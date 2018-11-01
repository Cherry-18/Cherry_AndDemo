package com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.ArticleBean;
import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.UserBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yjh on 2018/2/9.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    public static final String DATABASE_NAME = "mydb.db";

    private static DataBaseHelper instance;

    private Map<String,Dao> daos = new HashMap<>();

    public static synchronized DataBaseHelper getInstance(Context context){
        if(instance == null){
            synchronized (DataBaseHelper.class){
                if(instance == null){
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    private DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if(daos.containsKey(className)){
            dao = daos.get(className);
        }
        if(dao == null){
            try {
                dao = super.getDao(clazz);
                daos.put(className,dao);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, UserBean.class);
            TableUtils.createTable(connectionSource, ArticleBean.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,UserBean.class,true);
            TableUtils.dropTable(connectionSource,ArticleBean.class,true);
            onCreate(database,connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
