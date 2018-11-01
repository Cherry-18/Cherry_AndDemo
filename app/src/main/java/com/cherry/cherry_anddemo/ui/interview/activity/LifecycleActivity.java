package com.cherry.cherry_anddemo.ui.interview.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cherry.cherry_anddemo.R;

public class LifecycleActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {
    private Dialog tel_dialog;// 打电话弹出框
    private TextView tv_dialog;
    private TextView tv_intent;
    private TextView tv_lifecycle;
    private StringBuilder lifecycle;

    private static final String TAG = "cherry==========";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"LifecycleActivity---onCreate");
        lifecycle = new StringBuilder();
        lifecycle.append("onCreate===");
        setContentView(R.layout.cherry_activity_lifecycle);
        tv_intent = findViewById(R.id.tv_intent);
        tv_dialog = findViewById(R.id.tv_dialog);
        tv_lifecycle = findViewById(R.id.tv_lifecycle);
        tv_dialog.setOnClickListener(this);
        tv_intent.setOnClickListener(this);
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dialog:
                /**
                 * 验证 弹出dialog,dialogFragment时，7个生命周期都不会走
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(LifecycleActivity.this);
                builder.setTitle("name");
                builder.setMessage("测试");
                builder.setPositiveButton("确定", this);
                builder.setNegativeButton("取消", this);
                tel_dialog = builder.create();
                tel_dialog.show();

                new LifecycleModeDialog().show(getFragmentManager(),null);
                break;
            case R.id.tv_intent:
                Intent it = new Intent(LifecycleActivity.this, LifecycleSecondActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) { //确定
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(Uri.parse("tel:") + ""));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }else{
                this.startActivity(intent);
            }
        }
        if (i == -2){ //取消
            tel_dialog.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"LifecycleActivity---onStart");
        lifecycle.append("onStart===");
        tv_lifecycle.setText(lifecycle);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"LifecycleActivity---onStop");
        lifecycle.append("onStop===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"LifecycleActivity---onDestroy");
        lifecycle.append("onDestroy===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"LifecycleActivity---onPause");
        lifecycle.append("onPause===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"LifecycleActivity---onResume");
        lifecycle.append("onResume===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"LifecycleActivity---onRestart");
        lifecycle.append("onRestart===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG,"LifecycleActivity---onSaveInstanceState");
        lifecycle.append("onSaveInstanceState===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"LifecycleActivity---onRestoreInstanceState");
        lifecycle.append("onRestoreInstanceState===");
        tv_lifecycle.setText(lifecycle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG,"LifecycleActivity---onConfigurationChanged");
        lifecycle.append("onConfigurationChanged===");
        tv_lifecycle.setText(lifecycle);
    }
}
