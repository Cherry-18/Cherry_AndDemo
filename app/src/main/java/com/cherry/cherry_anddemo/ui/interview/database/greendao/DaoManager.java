package com.cherry.cherry_anddemo.ui.interview.database.greendao;

import com.cherry.cherry_anddemo.CherryApplication;
import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.DaoMaster;
import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

public class DaoManager {
    private static final String DB_NAME = "db_test";
    private volatile static DaoManager daoManager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoSession daoSession;
    private static DaoMaster daoMaster;

    public DaoManager() {
    }

    public static DaoManager getInstance(){
        if (daoManager == null){
            synchronized (DaoManager.class){
                if (daoManager == null){
                    daoManager = new DaoManager();
                }
            }
        }
        return daoManager;
    }

    public static DaoSession getDaoSession() {
        if (daoSession != null){
            daoSession.clear();
        }
        if (daoSession == null){
            synchronized (DaoManager.class){
                if (daoMaster == null){
                    daoMaster = getDaoMaster();
                }
                daoSession = daoMaster.newSession();
            }
        }
        return daoSession;
    }

    public static DaoMaster getDaoMaster(){
        if (daoMaster == null){
            // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
            // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
            MySqliteOpenHelper helper = new MySqliteOpenHelper(CherryApplication.getApplication(),DB_NAME,null);
            daoMaster = new DaoMaster(helper.getWritableDb());
            //daoMaster = new DaoMaster(helper.getEncryptedWritableDb("123")); //加密 uuid

        }
        return  daoMaster;
    }

    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public void closeConnect(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if (helper != null){
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession(){
        if (daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
    }
}
