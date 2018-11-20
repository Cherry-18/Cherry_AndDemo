package com.cherry.cherry_anddemo.ui.interview.observer.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cherry.cherry_anddemo.R;
import com.cherry.cherry_anddemo.ui.interview.observer.normal.EventCat;
import com.cherry.cherry_anddemo.ui.interview.observer.normal.EventMsg;
import com.cherry.cherry_anddemo.ui.interview.observer.normal.Observer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * FirstActivity 相当于是观察者
 */
public class FirstActivity extends AppCompatActivity {

    private TextView mObserverTv;
    private TextView mEventBusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mObserverTv = findViewById(R.id.tv_observer);
        mEventBusTv = findViewById(R.id.tv_show_eventbus_msg);

        observerTest();

        eventbusTest();

    }

    /**
     * 普通观察者例子
     */
    public void observerTest(){

//        EventCat.getDefault().register(new Observer() {
//            @Override
//            public void update(Object obj) {
//                mObserverTv.setText(obj.toString());
//            }
//        });

        EventCat.getDefault().register(new Observer() {
            @Override
            public void update(Object obj) {
                if (obj instanceof EventMsg){
                    EventMsg msg = (EventMsg)obj;
                    mObserverTv.setText("msg:"+msg.getMsg() + ",from:"+msg.getFrom());
                }
            }
        });

        findViewById(R.id.tv_go_twoactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * EventBus例子
     */
    public void eventbusTest(){
        //注册成为订阅者
        EventBus.getDefault().register(this);
    }


    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String string){
        mEventBusTv.setText(string);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当订阅者不再需要接受事件的时候，我们需要解除注册，释放内存
        EventBus.getDefault().unregister(this);
    }
}
