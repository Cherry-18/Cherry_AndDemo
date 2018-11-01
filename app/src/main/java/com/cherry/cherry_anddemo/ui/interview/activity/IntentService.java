package com.cherry.cherry_anddemo.ui.interview.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class IntentService extends Service{

    public String data = "";

    public IntentService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data = intent.getStringExtra("data_string");
        return super.onStartCommand(intent, flags, startId);
    }
}
