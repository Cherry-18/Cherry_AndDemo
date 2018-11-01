package com.cherry.cherry_anddemo.ui.interview.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cherry.cherry_anddemo.R;

/**
 * CallBack + Handler,监听服务的进程变化
 */
public class CallBackServiceActivity extends AppCompatActivity implements ServiceConnection{

    public CallBackService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_call_back_service);
        findViewById(R.id.callback_service_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            String data = bundle.getString("string");
            int data1 = bundle.getInt("int");
            boolean data2 = bundle.getBoolean("boolean");

            //接下来更新ui
        }
    };

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        myBinder = (CallBackService.MyBinder)iBinder;
        ((CallBackService.MyBinder) iBinder).getService().setOnServiceListener(new CallBackService.OnServiceListener() {

            //此方法提供给CallBackService在子线程中调用
            @Override
            public void onServiceListener(String data) {
                //这个方法是为CallBackService做耗时操作调用的

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("int",30);
                bundle.putString("string","idjojiw");
                bundle.putBoolean("boolean",true);
                message.setData(bundle);

                //通过Handler进行异步通信，不过耗时操作放在CallBackService中
                mHandler.sendMessage(message);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }


}
