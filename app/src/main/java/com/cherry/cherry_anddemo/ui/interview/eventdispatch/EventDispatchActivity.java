package com.cherry.cherry_anddemo.ui.interview.eventdispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cherry.cherry_anddemo.R;

public class EventDispatchActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button motionEventBtn;
    private Button motionEventBtn2;
    private MyLinearLayout motionEventLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        motionEventBtn = findViewById(R.id.btn_motion_event);
        motionEventBtn2 = findViewById(R.id.btn_motion_event2);
        motionEventLl = findViewById(R.id.ll_motion_event);
        motionEventBtn.setOnTouchListener(this);

        motionEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventDispatchActivity.this,"点击了motionevent按钮",Toast.LENGTH_SHORT).show();
            }
        });


        //事件分发
        //1、setOnTouchListener单独使用的时候返回值需要true,这样才能保证移动的时候获取相应的监听，而非一次监听（即只有按下事件）
        //返回false,表示没有被处理，将向父View传递。只能监听到view的"按下"事件，"移动"和"抬起"都不能监听到。因为down事件未结束
        //返回true,消耗此事件，表示正确接收并处理,不在分发。"按下""抬起""移动"都能监听到了

        //2、setOnTouchListener和setOnClickListener同时使用时，
        //返回true,事件被onTouch消耗掉了，因而不会在继续向下传递。只能监听"按下""抬起""移动",不能监听到"点击"；
        //返回false,"按下""抬起""移动""点击"都能监听

        motionEventLl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("MotionEvent=========","LINEARLAYOUT_ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("MotionEvent===========","LINEARLAYOUT_ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("MotionEvent===========","LINEARLAYOUT_ACTION_UP");
                        break;
                }
                return false;
            }
        });
        motionEventLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MotionEvent===========","onClick");
            }
        });
        motionEventLl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("MotionEvent===========","onLongClick");
                return false;
            }
        });

    }



    /**
     * onInterceptTouchEvent（），因该方法一旦返回一次true，就再也不会被调用了。
     *
     * dispatchtouchevent 为true表示被消费，事件终止，false则调用父类的onTouchEvent
     *
     * View 的onTouchEvent 方法默认都会消费掉事件（返回true），除非它是不可点击的
     * （clickable和longClickable同时为false），View的longClickable默认为false，
     * clickable需要区分情况，如Button的clickable默认为true，而TextView的clickable默认为false
     *
     * @return  如果onTouch返回值为true,表示这个Touch事件被onTouch方法处理完毕，不会把Touch事件再传递给Activity
     *          也就是说onTouchEvent方法不会被调用；如果onTouch返回值为false,表示这个Touch事件没有被tv完全处理，
     *          onTouch返回以后，Touch事件被传递给Activity,onTouchEvent方法调用。
     *
     *          如果onTouch()返回true， 则onTouchEvent()方法不会被调用，onClick()在onTouchEvent()中调用，
     *          所以点击无效，不会调用。三者优先级 onTouch->onTouchEvent->onClick。
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("MotionEvent========="," ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MotionEvent===========","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MotionEvent===========","ACTION_UP");
                break;
        }
        return false;
    }
}
