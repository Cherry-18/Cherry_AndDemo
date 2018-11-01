package com.cherry.cherry_anddemo.ui.interview.handlerthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cherry.cherry_anddemo.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handler + Thread + Loope    HandlerThread提供了主线程向子线程的通信
 * a.HandlerThread本质上是一个线程类，它继承了Thread。
   b.HandlerThread有自己内部的Looper对象，可以进行Looper循环。
   c.通过获取HandlerThread的Looper对象传递给Handler对象，可以在handlerMessage方法中执行异步任务。
   d.优点是不会有堵塞，减少对性能的消耗，缺点是不能进行多任务的处理，需要等待进行处理，处理效率较低。
   e.与线程池注重并发不同，HandlerThread是一个串行队列，HandlerThread背后只有一个线程。
 */
public class HandlerThreadActivity extends AppCompatActivity {

    private ImageView imageView;
    private HandlerThread handlerThread;

    private String url[] = {
            "https://b-ssl.duitang.com/uploads/item/201601/16/20160116131829_dSzAQ.jpeg",
            "https://www.zcool.com.cn/work/ZMjAyNjY4Njg=.html",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540819902210&di=1f82ec37e97712c85dd2149cef7227a3&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F728da9773912b31b20a1944a8b18367adbb4e180.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_handler_thread);
        imageView = findViewById(R.id.iv_handler_thread_img);

        handlerThread = new HandlerThread("downloadImage");
        handlerThread.start();

        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());

        for (int i = 0; i < url.length; i++) {
            childHandler.sendEmptyMessageDelayed(i, 1000 * i);
        }
    }

    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imageView.setImageBitmap((Bitmap)msg.obj);
        }
    };


    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap=null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap= BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    class ChildCallback implements Handler.Callback{

        @Override
        public boolean handleMessage(Message message) {
            //在子线程中进行网络请求
            Bitmap bitmap = downloadUrlBitmap(url[message.arg1]);
            Message message1 = new Message();
            message.obj = bitmap;
            //通知主线程去更新UI
            uiHandler.sendMessage(message1);
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
