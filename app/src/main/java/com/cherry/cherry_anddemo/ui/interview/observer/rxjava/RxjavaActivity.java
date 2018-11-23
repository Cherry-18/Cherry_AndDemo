package com.cherry.cherry_anddemo.ui.interview.observer.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cherry.cherry_anddemo.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxjavaActivity extends AppCompatActivity {
    private static final String TAG = "Rx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
    }

    public void rxObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d("RxjavaActivity====", "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d("RxjavaActivity====", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("RxjavaActivity====", "Error!");
            }
        };
    }

    public void rxSubscriber(){
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                //它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，
                //例如数据的清零或重置。它总是在 subscribe 所发生的线程被调用，而不能指定线程。
                super.onStart();
            }

            @Override
            public void onNext(String s) {
                Log.d("RxjavaActivity====", "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d("RxjavaActivity====", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("RxjavaActivity====", "Error!");
            }
        };

        //这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅。在这个方法被调用后，Subscriber 将不再接收事件。
        //因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，这个引用如果不能及时被释放，将有内存泄露的风险。
        subscriber.unsubscribe();
        //一般在这个方法调用前，可以使用 isUnsubscribed() 先判断一下状态
        subscriber.isUnsubscribed();
    }

    public void rxObservable(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("cherry");
                subscriber.onCompleted();
            }
        });
    }


    /**
     * 分步骤实现Rxjava
     */
    public void divideStep() {
        io.reactivex.Observable<Integer> observable = io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "开始采用subscribe链接");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "开始响应" + value + "事件");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "对error事件进行响应");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "对complete事件进行响应");
            }
        };
        observable.subscribe(observer);
    }

    private Disposable mDisposable;
    //基于事件流的链式调用方式
    public void eventFlow() {

        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext(4);
                e.onNext(5);
                e.onNext(6);
                e.onNext(7);
                e.onError(new ClassCastException("rxjavaerror"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("rxjava---->eventFlow", "开始采用subscribe链接");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
//                Log.e("rxjava---->eventFlow", "开始响应" + value + "事件");
                if(value==6){
                    mDisposable.dispose();//在接受到第6个事件后切断观察者和被观察者之间的联系
                    Log.e("rxjava---->eventFlow", "已经切断了连接" +mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.e("rxjava---->eventFlow", "对complete事件进行响应");

            }
        });
    }

}
