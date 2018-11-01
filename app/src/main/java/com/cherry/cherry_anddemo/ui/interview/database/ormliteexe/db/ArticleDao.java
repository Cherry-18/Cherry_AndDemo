package com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.db;

import android.content.Context;

import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.ArticleBean;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yjh on 2018/2/11.
 */

public class ArticleDao {
    private Context context;
    private Dao<ArticleBean, Integer> dao;

    public ArticleDao(Context context){
        this.context = context;
        this.dao = DataBaseHelper.getInstance(context).getDao(ArticleBean.class);
    }

    public void insert(ArticleBean data){
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ArticleBean data){
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ArticleBean data){
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArticleBean queryById(int id){
        ArticleBean article = null;
        try {
            article = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public List<ArticleBean> queryByUserId(int user_id){
        try {
            return dao.queryBuilder().where().eq("user_id",user_id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
