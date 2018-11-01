package com.cherry.cherry_anddemo.ui.interview.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BindService extends Service{
    public String data = "";

    public BindService() { }

    /**
     * @Return 返回与service通信的渠道
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder{
        public void setData(String data){
            BindService.this.data = data;
        }
    }
}
