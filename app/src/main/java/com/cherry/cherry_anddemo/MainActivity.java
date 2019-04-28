package com.cherry.cherry_anddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cherry.cherry_anddemo.ui.androidnewfeature.RippleDrawableActivity;
import com.cherry.cherry_anddemo.ui.interview.activity.IntentActivity;
import com.cherry.cherry_anddemo.ui.interview.activity.LifecycleActivity;
import com.cherry.cherry_anddemo.ui.interview.asynctask.AsyncTaskActivity;
import com.cherry.cherry_anddemo.ui.interview.broadcast.BroadcastActivity;
import com.cherry.cherry_anddemo.ui.interview.eventdispatch.EventDispatchActivity;
import com.cherry.cherry_anddemo.ui.interview.handler.HandlerActivity;
import com.cherry.cherry_anddemo.ui.interview.handlerthread.HandlerThreadActivity;
import com.cherry.cherry_anddemo.ui.interview.leakcanary.LeakSingle;
import com.cherry.cherry_anddemo.ui.interview.map.LocationActivity;
import com.cherry.cherry_anddemo.ui.interview.map.MapActivity;
import com.cherry.cherry_anddemo.ui.interview.observer.eventbus.FirstActivity;
import com.cherry.cherry_anddemo.ui.interview.storage.sqlite.SqliteActivity;
import com.cherry.cherry_anddemo.ui.interview.webview.JsInteractionActivity;
import com.cherry.cherry_anddemo.ui.moduletest.ModuleTestActivity;

public class MainActivity extends AppCompatActivity {

//    private TextView mLeakTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_main);
//        mLeakTv = findViewById(R.id.tv_Leak);
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
        findViewById(R.id.btn_location_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(LocationActivity.class);
            }
        });
        findViewById(R.id.btn_map_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(MapActivity.class);
            }
        });
        findViewById(R.id.btn_first_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityOther(FirstActivity.class);
            }
        });
        findViewById(R.id.btn_intent_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                intent.putExtra("intentTest","我是intentTest");
                startActivity(intent);

            }
        });
        findViewById(R.id.btn_moduletest_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ModuleTestActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.btn_ripple_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RippleDrawableActivity.class);
                startActivity(intent);

            }
        });

//        leakcanary();
    }

    public void startActivityOther(Class clazz){
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }


    public void leakcanary(){
//        LeakSingle.getInstance(this.getApplication()).setRetainedTextView(mLeakTv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内泄露
//        LeakSingle.getInstance(this.getApplication()).removeRetainedTextView();
    }
}
