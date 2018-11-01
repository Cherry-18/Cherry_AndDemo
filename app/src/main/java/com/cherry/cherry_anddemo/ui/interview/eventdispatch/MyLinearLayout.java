package com.cherry.cherry_anddemo.ui.interview.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MotionEvent========","onTouchEvent,action="+event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://0
                Log.e("TAG", "LinearLayout onTouchEvent 按住");
                break;
            case MotionEvent.ACTION_UP:  //1
                Log.e("TAG", "LinearLayout onTouchEvent onTouch抬起");
                break;
            case MotionEvent.ACTION_MOVE://2
                Log.e("TAG", "LinearLayout onTouchEvent 移动");
                break;
        }
        return super.onTouchEvent(event);

//        return true;  //TODO 如果直接返回true，onTouchEvent会调用,但是onClick()不会调用。
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}

