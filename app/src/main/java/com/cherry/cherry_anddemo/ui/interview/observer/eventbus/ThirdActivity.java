package com.cherry.cherry_anddemo.ui.interview.observer.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cherry.cherry_anddemo.R;
import com.cherry.cherry_anddemo.ui.interview.observer.normal.EventCat;
import com.cherry.cherry_anddemo.ui.interview.observer.normal.EventMsg;

/**
 * ThirdActivity 相当于是被观察者
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        findViewById(R.id.btn_post_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventCat.getDefault().post(new String("我是被观察者发送的消息！！"));
            }
        });


        final EventMsg msg = new EventMsg("我是被观察者发送的消息","被观察者");

        findViewById(R.id.btn_post_msg1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventCat.getDefault().post(msg);
            }
        });
    }
}
