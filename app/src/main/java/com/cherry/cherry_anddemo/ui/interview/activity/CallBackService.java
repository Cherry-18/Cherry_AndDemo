package com.cherry.cherry_anddemo.ui.interview.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class CallBackService extends Service{

    //在Service中如果要进行耗时任务，可以通过CallBack接口提供的方法与Activity进行通信
    private OnServiceListener onServiceListener;
    private String data = "";

    public CallBackService() { }

    public OnServiceListener getOnServiceListener() {
        return onServiceListener;
    }

    public void setOnServiceListener(OnServiceListener onServiceListener) {
        this.onServiceListener = onServiceListener;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public interface OnServiceListener{
        void onServiceListener(String data);
    }

    class MyBinder extends Binder{
        public CallBackService getService(){
            return  CallBackService.this;
        }
    }
}
