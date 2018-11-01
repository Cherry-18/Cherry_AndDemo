package com.cherry.cherry_anddemo.ui.interview.storage.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "sqlite========";
    public static final int VERSION = 1;

    //必须要有构造函数
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 当第一次创建数据库的时候，调用该方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table sqlite_table(id int primary key, name varchar(20), age int, sex varchar(10))";
        //输出创建数据库的日志信息
        Log.e(TAG, "create Database------------->");
        //execSQL函数用于执行SQL语句
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->");
        String sql = "DROP TABLE IF EXISTS sqlite_table";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
