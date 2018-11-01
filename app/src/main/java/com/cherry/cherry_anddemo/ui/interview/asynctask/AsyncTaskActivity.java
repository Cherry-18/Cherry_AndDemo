package com.cherry.cherry_anddemo.ui.interview.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cherry.cherry_anddemo.R;

/**
 * AsyncTask 是封装了线程池和Handler的异步框架
 */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mShowPicIv;
    private Button mDownLoadPicBtn;
    private String imageUrl = "https://b-ssl.duitang.com/uploads/item/201601/16/20160116131829_dSzAQ.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_asynctask);
        mShowPicIv = findViewById(R.id.iv_show_pic);
        mDownLoadPicBtn = findViewById(R.id.btn_download_pic);
        mDownLoadPicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_download_pic){
            new PictureAsyncTask(mShowPicIv).execute(imageUrl);
        }
    }
}
