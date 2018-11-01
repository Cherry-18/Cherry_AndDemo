package com.cherry.cherry_anddemo.ui.interview.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cherry.cherry_anddemo.R;

import java.lang.ref.WeakReference;

/**
 * 异步消息机制
 */
public class HandlerActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mHandlerMsgTv;
    private Button mHandlerSendBtn;
    private int handlerMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_handler);
        mHandlerMsgTv = findViewById(R.id.handler_msg);
        mHandlerSendBtn = findViewById(R.id.handler_send);
        mHandlerSendBtn.setOnClickListener(this);

        new ThreadA().start();
    }


    /**====================mHandler 用于子线程和主线程之间异步通信====================*/
    /**====================mHandler 用于子线程和主线程之间异步通信====================*/

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handlerMsg = msg.arg1;
            mHandlerMsgTv.setText(handlerMsg+"");
        }
    };


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.handler_send){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.arg1 = 1000;
                    mHandler.sendMessage(message);
                }
            }).start();
            new ThreadB().start();

        }
    }



    /**===================threadHandler 用于子线程和子线程之间异步通信===================*/
    /**===================threadHandler 用于子线程和子线程之间异步通信===================*/

    private Handler threadHandler;

    class ThreadA extends Thread{
        @Override
        public void run() {
            super.run();
            Looper.prepare();

            threadHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //收到来自于ThreadBde消息，注意这里运行在A线程中
                    Log.e("Handler======",msg.arg1+"");
                }
            };

            Looper.loop();
        }
    }

    class ThreadB extends Thread{
        @Override
        public void run() {
            super.run();

            Looper looper = Looper.myLooper();

            Message message = new Message();
            message.arg1 = 2000;
            threadHandler.sendMessage(message);
        }
    }


    /**================================================================*/
    /**================================================================*/

    /**
     * 在java中非静态内部类和匿名内部类都会隐式持有当前类的外部引用，
     * 由于Handler是非静态内部类所以其持有当前Activity的隐式引用，
     * 如果Handler没有被释放，其所持有的外部引用也就是Activity也不可能被释放，
     * 当一个对象不需要再使用了，本来该被回收时，
     * 而有另外一个正在使用的对象持有它的引用从而导致它不能被回收，
     * 这导致本该被回收的对象不能被回收而停留在堆内存中，这就产生了内存泄漏.
     *
     * 解决方法：使用静态内部类并继承Handler。因为静态的内部类不会持有外部类的引用，
     * 所以不会导致外部类实例的内存泄露。当你需要在静态内部类中调用外部的Activity时，
     * 我们可以使用弱引用来处理。另外关于同样也需要将Runnable设置为静态的成员属性。
     */

    private static class MyHandler extends Handler{
        //持有弱引用HandlerActivity,GC回收时会被回收掉.
        private final WeakReference<HandlerActivity> mActivity;

        private MyHandler(HandlerActivity activity) {
            mActivity = new WeakReference<HandlerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = mActivity.get();
            super.handleMessage(msg);
            if (mActivity != null){
                //执行业务逻辑
            }
        }
    }

    private static final Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            //执行业务逻辑
        }
    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.cherry_activity_handler);
//        MyHandler myHandler = new MyHandler(this);
//
//        //解决了内存泄漏,延迟5分钟后发送
//        myHandler.postDelayed(myRunnable, 1000 * 60 * 5);
//    }
}
