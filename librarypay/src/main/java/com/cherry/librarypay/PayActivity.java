package com.cherry.librarypay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cherry.librarycommon.utils.ToastUtils;

@Route(path = "/librarypay/PayActivity")
public class PayActivity extends AppCompatActivity {

    //使用 @Autowired注解方式获取 ShopActivity调转到PayActivity时携带的值，
    //此时需要将  ARouter.getInstance().inject(this);  添加到onCreate中
    //name = "isShopJump"  是跳转过来携带数据的 key
    @Autowired(name = "isShopJump")
    public boolean isShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        ARouter.getInstance().inject(this);
        ToastUtils.toast(PayActivity.this,"isShopJump="+isShop);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //路由简单跳转
                ARouter.getInstance().build("/libraryshop/ShopActivity").navigation();

                //路由携带数据的跳转
                //ARouter.getInstance().build("/libraryshop/ShopActivity")
                    //.withLong("key1", 666L)
                    //.withString("key3", "888")
                    //.navigation();
            }
        });

    }
}
