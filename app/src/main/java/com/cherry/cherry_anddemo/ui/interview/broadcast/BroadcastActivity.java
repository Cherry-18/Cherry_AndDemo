package com.cherry.cherry_anddemo.ui.interview.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cherry.cherry_anddemo.R;


/**
 * 一个receiver只有一个intentfilter，一个intentfilter中可以有多个action。
 * 多个receiver可以注册同一个action。
 * 1、创建需要启动的BroadcastReceiver的Intent。
 * 2、调用Context的sendBroadcast()或sendOrderedBroadcast()方法来启动指定的BroadcastReceiver。
 *    当应用程序发出一个Broadcast Intent之后，所有匹配该Intent的BroadcastReceiver都有可能被启动。
 *
 * 区别：
 *    动态注册的广播接收器可以自由地控制注册与注销
 *    但是它也存在着一个缺点，即必须要在程序启动之后才能接收到广播，因为注册的逻辑是写在onCreate()方法中的。
 *
 *    静态注册的广播接收器能在程序未启动的情况下就能接收到广播。
 */
public class BroadcastActivity extends AppCompatActivity {

    private BroadcastReceiverOne receiverOne;
    private BroadcastReceiverTwo receiverTwo;
    private Button mBroadcastBtn0;
    private Button mBroadcastBtn1;
    private Button mBroadcastBtn2;
    private Button mBroadcastBtn3;
    private Button mBroadcastBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_broadcast);
        mBroadcastBtn0 = findViewById(R.id.broadcast_btn0);
        mBroadcastBtn1 = findViewById(R.id.broadcast_btn1);
        mBroadcastBtn2 = findViewById(R.id.broadcast_btn2);
        mBroadcastBtn3 = findViewById(R.id.broadcast_btn3);
        mBroadcastBtn4 = findViewById(R.id.broadcast_btn4);
        setListener();

        /**==============================动态注册========================== */
        /** 动态注册的顺序与manifest中的顺序一致 */

        /**
         * 动态注册---BroadcastReceiverTwo
         */
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("com.cherry.broadcast.test1");
        intentFilter1.setPriority(2000);
        receiverTwo = new BroadcastReceiverTwo();
        registerReceiver(receiverTwo,intentFilter1);

        /**
         * 动态注册---BroadcastReceiverOne
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.cherry.broadcast.test1");
        intentFilter.setPriority(1000);
        receiverOne = new BroadcastReceiverOne();
        registerReceiver(receiverOne,intentFilter);
        /**==============================动态注册========================== */


        /**
         * 本地广播注册
         */
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BroadcastActivity.this);
        intentFilter.addAction("com.cherry.broadcast.test2");
        receiverOne = new BroadcastReceiverOne();
        localBroadcastManager.registerReceiver(receiverTwo,intentFilter);
    }


    public void setListener(){
        /**
         * 静态注册普通广播
         * sendBroadcast 会按着manifest中的顺序进行发送
         */
        mBroadcastBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.cherry.broadcast.test");
                sendBroadcast(intent);
            }
        });

        /**
         * 静态注册有序广播
         * sendOrderedBroadcast 如果不指定优先级，会按着manifest中的顺序进行发送
         */
        mBroadcastBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.cherry.broadcast.test");
                sendOrderedBroadcast(intent,null);
            }
        });

        /**
         * 动态注册 普通广播
         */
        mBroadcastBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.cherry.broadcast.test1");
                sendBroadcast(intent);
            }
        });

        /**
         * 动态注册 本地广播
         */
        mBroadcastBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.cherry.broadcast.test2");
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BroadcastActivity.this);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        /**
         * 动态注册 有序广播
         */
        mBroadcastBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.cherry.broadcast.test1");
                intent.putExtra("msg","我是原信息");
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverOne);//一定要记得取消广播接收器的注册
        unregisterReceiver(receiverTwo);//一定要记得取消广播接收器的注册
    }
}
