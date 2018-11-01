package com.cherry.cherry_anddemo.ui.interview.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cherry.cherry_anddemo.R;

public class LifecycleSecondActivity extends AppCompatActivity {

    private TextView tv_secondlifecycle;
    private StringBuilder lifecycle;

    private static final String TAG = "cherry==========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"LifecycleSecondActivity---onCreat");
        lifecycle = new StringBuilder();
        lifecycle.append("onCreate===");
        setContentView(R.layout.cherry_activity_second_lifecycle);
        tv_secondlifecycle = findViewById(R.id.tv_second_lifecycle);
        tv_secondlifecycle.setText(lifecycle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//以竖屏显示
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//以横屏显示

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"LifecycleSecondActivity---onStart");
        lifecycle.append("onStart===");
        tv_secondlifecycle.setText(lifecycle);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"LifecycleSecondActivity---onStop");
        lifecycle.append("onStop===");
        tv_secondlifecycle.setText(lifecycle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"LifecycleSecondActivity---onDestroy");
        lifecycle.append("onDestroy===");
        tv_secondlifecycle.setText(lifecycle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"LifecycleSecondActivity---onPause");
        lifecycle.append("onPause===");
        tv_secondlifecycle.setText(lifecycle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"LifecycleSecondActivity---onResume");
        lifecycle.append("onResume===");
        tv_secondlifecycle.setText(lifecycle);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"LifecycleSecondActivity---onRestart");
        lifecycle.append("onRestart===");
        tv_secondlifecycle.setText(lifecycle);
    }
}
