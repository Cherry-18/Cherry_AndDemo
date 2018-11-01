package com.cherry.cherry_anddemo.ui.interview.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * AsyncTask　<Params, Progress, Result>
 * Params: 这个泛型指定的是我们传递给异步任务执行时的参数的类型
 * Progress: 这个泛型指定的是我们的异步任务在执行的时候将执行的进度返回给UI线程的参数的类型
 * Result: 这个泛型指定的异步任务执行完后返回给UI线程的结果的类型
 */
public class PictureAsyncTask extends AsyncTask<String,Integer,Bitmap>{

    private ImageView mShowIv;

    public PictureAsyncTask(ImageView mShowIv) {
        this.mShowIv = mShowIv;
    }

    /**
     * 这里是在异步操作之前执行，运行在UI线程，
     * 一般显示给用户：此时即将要去加载图片了
     * 此处可将progressBar设置为可见
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 这里执行耗时操作，此例子中，是网络请求图片的操作
     */
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        String imageUrl = strings[0];

        try {
            URL url = new URL(imageUrl);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.e("AsyncTask=====",bitmap.toString());
        publishProgress(22);//此方法的调用将会触发onProgressUpdatef方法的调用

        return bitmap;
    }

    /**
     * 此方法运行于UI线程,一般用来更新进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        //progressBar.setProgress(values[0]);
    }

    /**
     * 此方法中bitmap是doInBackground执行完成后返回的，
     * 而且此方法运行在UI线程，更新UI
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mShowIv.setImageBitmap(bitmap);
    }
}
