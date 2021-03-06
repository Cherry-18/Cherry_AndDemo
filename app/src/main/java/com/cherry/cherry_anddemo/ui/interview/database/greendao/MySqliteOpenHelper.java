package com.cherry.cherry_anddemo.ui.interview.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.DaoMaster;
import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.UserDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

public class MySqliteOpenHelper  extends DaoMaster.DevOpenHelper{

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //todo 加入所有的 xxxDao.class
        MigrationHelper.migrate(db, UserDao.class);
    }
}
