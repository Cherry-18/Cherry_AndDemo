package com.cherry.cherry_anddemo.ui.moduletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cherry.cherry_anddemo.R;
import com.cherry.librarycommon.utils.ToastUtils;
import com.cherry.libraryshop.ShopActivity;

public class ModuleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_test);

        findViewById(R.id.btn_moduletest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toast(ModuleTestActivity.this,"调用了common的方法");
            }
        });

        findViewById(R.id.btn_goto_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toast(ModuleTestActivity.this,"gotopay");
            }
        });

        findViewById(R.id.btn_goto_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.toast(ModuleTestActivity.this,"gotoshop");
                startActivity(new Intent(ModuleTestActivity.this, ShopActivity.class));
            }
        });
    }
}
