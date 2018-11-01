package com.cherry.cherry_anddemo.ui.interview.database.ormliteexe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.cherry.cherry_anddemo.R;
import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.ArticleBean;
import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.UserBean;
import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.db.ArticleDao;
import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.db.UserDao;
import com.j256.ormlite.dao.ForeignCollection;

import java.util.Date;
import java.util.Iterator;

public class OrmliteActivity extends AppCompatActivity {

    private TextView mTv;
    private StringBuffer contentBuffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_ormlite);
        mTv = (TextView)findViewById(R.id.tv);
        contentBuffer = new StringBuffer();
        initData();
        initView();
    }

    public void initData(){
        UserBean userData = new UserBean("张三",1,new Date(),"北京");
        new UserDao(OrmliteActivity.this).insert(userData);
        ArticleBean articleData = new ArticleBean("标题","内容内容内容",userData);
        new ArticleDao(OrmliteActivity.this).insert(articleData);
    }

    public void initView(){
        ArticleBean articleBean = new ArticleDao(OrmliteActivity.this).queryById(1);
        contentBuffer.append(articleBean.toString());

        UserBean userBean = new UserDao(OrmliteActivity.this).queryById(articleBean.getUser_id().getId());
        contentBuffer.append("\n\n"+userBean.toString());

        ForeignCollection<ArticleBean> articles = userBean.getArticles();
        Iterator<ArticleBean> iterator = articles.iterator();
        contentBuffer.append("\n\n");
        while(iterator.hasNext()){
            ArticleBean article = iterator.next();
            contentBuffer.append(article.toString()+"\n");
        }
        mTv.setText(contentBuffer.toString());
    }
}
