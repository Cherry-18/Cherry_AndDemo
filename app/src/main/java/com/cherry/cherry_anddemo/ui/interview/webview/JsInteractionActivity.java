package com.cherry.cherry_anddemo.ui.interview.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cherry.cherry_anddemo.R;

/**
 * Android 和 H5 交互，实际上是与html中的JavaScript进行交互
 * js_android_inter.html这个是进行交互的html页面
 */

public class JsInteractionActivity extends AppCompatActivity {

    private static final String TAG = "OrmliteActivity";
    private WebView mWebView;
    private WebSettings webSettings;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_js_android_interact);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/js_android_inter.html");//加载本地asset下面的js_android_inter.html文件
        //mWebView.loadUrl("https://www.baidu.com/");//加载本地assets下面的js_android_inter.html文件

        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//打开js支持

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        mWebView.setWebViewClient(new WebViewClient(){
            /**
             * 是否在 WebView 内加载页面
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.equals("file:///android_asset/js_android_inter.html")) {
                    Log.e(TAG, "shouldOverrideUrlLoading: " + url);
                    finish();
                    return true;
                } else {
                    mWebView.loadUrl(url);
                    return false;
                }
            }

            /**
             * 当WebView得页面Scale值发生改变时回调
             */
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
            }

            /**
             * WebView 开始加载页面时回调，一次Frame加载对应一次回调
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            /**
             * WebView 完成加载页面时回调，一次Frame加载对应一次回调
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            /**
             * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，
             * 除非本地有当前 url 对应有缓存，否则就会加载。
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            /**
             * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
             */
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            /**
             * WebView 访问 url 出错
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            /**
             * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            /**
             * 当前 WebView 加载网页进度
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            /**
             * Js 中调用 alert() 函数，产生的对话框
             */
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            /**
             * 处理 Js 中的 Confirm 对话框
             */
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            /**
             * 处理 JS 中的 Prompt对话框
             */
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            /**
             * 接收web页面的 Title
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            /**
             * 接收web页面的icon
             */
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });


        /**
         * 打开js接口給H5调用，参数1为本地类名，参数2 "android" 为别名；
         * h5用window.别名.类名里的方法名才能调用方法里面的内容，例如：window.android.back();
         */
        mWebView.addJavascriptInterface(new JsInteration(this), "android");
    }


    /**====================================================================================*/
    /**====================================================================================*/


    /**
     * 创建要注入的 Java 类
     * 自己写一个类，里面是提供给H5访问的方法
     */
    public class JsInteration {
        private Context mContext;

        public JsInteration() {
        }

        public JsInteration(Context mContext) {
            this.mContext = mContext;
        }

        @JavascriptInterface//一定要写，不然H5调不到这个方法
        public String back() {
            return "我是java里的方法返回值";
        }

        @JavascriptInterface
        public void hello() {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
        }
    }

    //点击按钮，访问H5里带返回值的方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        Log.e("TAG", "onClick: ");
//        mWebView.loadUrl("JavaScript:show()");//直接访问H5里不带返回值的方法，show()为H5里的方法

        //传固定字符串可以直接用单引号括起来
        mWebView.loadUrl("javascript:alertMessage('哈哈')");//访问H5里带参数的方法，alertMessage(message)为H5里的方法

        //当出入变量名时，需要用转义符隔开
        String content="9880";
        mWebView.loadUrl("javascript:alertMessage(\""   +content+   "\")"   );

        //Android调用有返回值js方法，安卓4.4以上才能用这个方法
        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d(TAG, "js返回的结果为=" + value);
                Toast.makeText(JsInteractionActivity.this,"js返回的结果为=" + value,Toast.LENGTH_LONG).show();
            }
        });
    }



    /**====================================================================================*/
    /**
     * 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
     * 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
     */
    @Override
    protected void onResume() {
        super.onResume();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webSettings.setJavaScriptEnabled(false);
    }
}
