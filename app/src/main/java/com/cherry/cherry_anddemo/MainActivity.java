package com.cherry.cherry_anddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cherry.cherry_anddemo.ui.interview.activity.LifecycleActivity;
import com.cherry.cherry_anddemo.ui.interview.asynctask.AsyncTaskActivity;
import com.cherry.cherry_anddemo.ui.interview.broadcast.BroadcastActivity;
import com.cherry.cherry_anddemo.ui.interview.eventdispatch.EventDispatchActivity;
import com.cherry.cherry_anddemo.ui.interview.handler.HandlerActivity;
import com.cherry.cherry_anddemo.ui.interview.handlerthread.HandlerThreadActivity;
import com.cherry.cherry_anddemo.ui.interview.storage.sqlite.SqliteActivity;
import com.cherry.cherry_anddemo.ui.interview.webview.JsInteractionActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * hahahhaha
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_main);

        findViewById(R.id.tv_js_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(JsInteractionActivity.class);
            }
        });
        findViewById(R.id.tv_lifecycle_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(LifecycleActivity.class);
            }
        });
        findViewById(R.id.tv_broadcast_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(BroadcastActivity.class);
            }
        });
        findViewById(R.id.btn_handler_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(HandlerActivity.class);
            }
        });
        findViewById(R.id.btn_asynctask_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(AsyncTaskActivity.class);
            }
        });
        findViewById(R.id.btn_handlerthread_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(HandlerThreadActivity.class);
            }
        });
        findViewById(R.id.btn_eventdispatch_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(EventDispatchActivity.class);
            }
        });
        findViewById(R.id.btn_sqlite_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(SqliteActivity.class);
            }
        });
    }

    public void startActivityOther(Class clazz){
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }


}
