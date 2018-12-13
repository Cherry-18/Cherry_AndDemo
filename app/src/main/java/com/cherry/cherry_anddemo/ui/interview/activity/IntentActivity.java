package com.cherry.cherry_anddemo.ui.interview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cherry.cherry_anddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentActivity extends AppCompatActivity {

    // 安装完成ButterKnife插件后,测试的过程中要注意的是，需要将光标移到
    // setContentView(R.layout.acty_login)，将光标放到R.layout.acty_login，
    // 然后右键Generate就有了。

    @BindView(R.id.textView2)
    TextView tv2;
    @BindView(R.id.textView3)
    TextView tv3;
    @BindView(R.id.textView4)
    TextView tv4;
    @BindView(R.id.textView5)
    TextView tv5;

    private TextView intentTv;
    private String intentStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        ButterKnife.bind(this);
        intentTv = findViewById(R.id.textView2);

        if (getIntent() != null) {
            intentStr = getIntent().getStringExtra("intentTest");
            //如果 MianActivity 没有传值过来，intentStr=null
            if (!TextUtils.isEmpty(intentStr)) {
                intentTv.setText(intentStr);
            }
        }

    }

    @OnClick({R.id.textView2, R.id.textView3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView2:
                break;
            case R.id.textView3:
                break;
        }
    }
}
