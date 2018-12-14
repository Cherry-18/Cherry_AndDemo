package com.cherry.librarycommon.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils{

    public static void toast(Context context, String toastStr){
        Toast.makeText(context, toastStr,Toast.LENGTH_SHORT).show();
    }
}
