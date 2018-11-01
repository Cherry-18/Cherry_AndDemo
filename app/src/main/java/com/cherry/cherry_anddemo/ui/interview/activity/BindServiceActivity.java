package com.cherry.cherry_anddemo.ui.interview.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cherry.cherry_anddemo.R;

public class BindServiceActivity extends AppCompatActivity implements ServiceConnection {

    private BindService.MyBinder myBinder;
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_bind_service);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        /**
         * 绑定服务
         */
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BindServiceActivity.this, BindService.class);
                bindService(intent, BindServiceActivity.this, BIND_AUTO_CREATE);
            }
        });

        /**
         * 通过binder对象来和Service进行通信
         */
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myBinder != null) {
                    myBinder.setData("btn2");
                }
            }
        });

        /**
         * 通过binder对象来和Service进行通信
         */
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myBinder != null) {
                    myBinder.setData("btn3");
                }
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        myBinder = (BindService.MyBinder) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }


}
