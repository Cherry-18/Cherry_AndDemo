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

/**
 * SecondActivity 相当于是观察者
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mObserver2Tv;
    private TextView mShowStickyEventTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mObserver2Tv = findViewById(R.id.tv_observer2);
        mShowStickyEventTv = findViewById(R.id.tv_show_sticky_event);

        observerTest();

        findViewById(R.id.tv_send_eventbus_msg).setOnClickListener(this);
        findViewById(R.id.tv_send_sticky_eventbus_msg).setOnClickListener(this);
        findViewById(R.id.tv_register_eventbus).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_send_eventbus_msg:
                eventBusTest();
                break;
            case R.id.tv_send_sticky_eventbus_msg:
                EventBus.getDefault().postSticky(new String("我是eventbus发送的粘性事件！！"));
                break;
            case R.id.tv_register_eventbus:
                EventBus.getDefault().register(this);
                break;
            default:
                break;
        }
    }


    /**
     * 普通观察者
     */
    public void observerTest(){

        EventCat.getDefault().register(new Observer() {
            @Override
            public void update(Object obj) {
                if (!(obj instanceof EventMsg)){
                    mObserver2Tv.setText(obj.toString());
                }
            }
        });

        findViewById(R.id.tv_go_threeactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * eventbus
     */
    public void eventBusTest(){
        EventBus.getDefault().post(new String("我是eventbus发送的消息！！"));
    }


    /**
     * 该广播发送后，会保存在内存中，如果后来有注册的Receiver与之匹配，那么该Receiver便会接收到该广播。
     * 那么粘性事件同理，在注册之前便把事件发生出去，等到注册之后便会收到最近发送的粘性事件（必须匹配）。
     * 注意：只会接收到最近发送的一次粘性事件，之前的会接受不到。
     */
    @Subscribe(sticky = true)
    public void onEvent(String string){
        mShowStickyEventTv.setText(string);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
