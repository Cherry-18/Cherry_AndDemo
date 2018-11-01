package com.cherry.cherry_anddemo.ui.interview.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cherry.cherry_anddemo.R;


/**
 * Author: yangjinhua
 * 上传身份证相机、相册弹窗
 */
public class LifecycleModeDialog extends DialogFragment implements View.OnClickListener {
    private OnIDcardPhotoListener onIDcardPhotoListener;

    public void setOnIDcardPhotoListener(OnIDcardPhotoListener onIDcardPhotoListener) {
        this.onIDcardPhotoListener = onIDcardPhotoListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity= Gravity.CENTER;
        window.setAttributes(attributes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cherry_layout_lifecycle_mode, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(true);
        view.findViewById(R.id.wallet_upload_idcard_capture_tv).setOnClickListener(this);
        view.findViewById(R.id.wallet_upload_idcard_picture_tv).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.wallet_upload_idcard_capture_tv) {
            onIDcardPhotoListener.onCapture();
        } else if (i == R.id.wallet_upload_idcard_picture_tv) {
            onIDcardPhotoListener.onPicture();
        }
    }

    public interface OnIDcardPhotoListener{
        void onPicture(); //相册
        void onCapture(); //相机
    }
}
