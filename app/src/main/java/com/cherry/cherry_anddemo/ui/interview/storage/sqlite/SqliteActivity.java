package com.cherry.cherry_anddemo.ui.interview.storage.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cherry.cherry_anddemo.R;

/**
 * TODO 有错误
 */
public class SqliteActivity extends AppCompatActivity {
    private Button createBtn;
    private Button insertBtn;
    private Button updateBtn;
    private Button queryBtn;
    private Button deleteBtn;
    private Button ModifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_sqlite);
        //调用creatView方法
        creatView();
        //setListener方法
        setListener();
    }

    //通过findViewById获得Button对象的方法
    private void creatView(){
        createBtn = (Button)findViewById(R.id.createDatabase);
        updateBtn = (Button)findViewById(R.id.updateDatabase);
        insertBtn = (Button)findViewById(R.id.insert);
        ModifyBtn = (Button)findViewById(R.id.update);
        queryBtn = (Button)findViewById(R.id.query);
        deleteBtn = (Button)findViewById(R.id.delete);
    }

    //为按钮注册监听的方法
    private void setListener(){
        createBtn.setOnClickListener(new CreateListener());
        updateBtn.setOnClickListener(new UpdateListener());
        insertBtn.setOnClickListener(new InsertListener());
        ModifyBtn.setOnClickListener(new ModifyListener());
        queryBtn.setOnClickListener(new QueryListener());
        deleteBtn.setOnClickListener(new DeleteListener());
    }


    /**
     * 创建数据库的方法
     */
    class CreateListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,1);
            //得到一个可读的SQLiteDatabase对象
            SQLiteDatabase db = helper.getReadableDatabase();
        }
    }

    /**
     *  更新数据库的方法
     */
    class UpdateListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,2);
            SQLiteDatabase db = helper.getReadableDatabase();
        }
    }

    /**
     * 插入数据的方法
     */
    class InsertListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,1);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id",1001);
            values.put("name","哈哈哈");
            values.put("age",32);
            values.put("sex","男");
            ContentValues values1 = new ContentValues();
            values.put("id",1002);
            values.put("name","呼呼呼");
            values.put("age",31);
            values.put("sex","女");
            db.insert("sqlite_table",null,values);
            db.insert("sqlite_table",null,values1);
            db.close();
        }
    }

    /**
     * 查询数据的方法
     */
    class  QueryListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,1);
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query("sqlite_table",new String[]{"id","sname","sage","ssex"},"id=?",new String[1001],null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                Log.e("sqlite========", "姓名："+name+" "+"年龄："+age+" "+"性别："+sex);
            }
            db.close();
        }
    }

    /**
     * 修改数据的方法
     */
    class ModifyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,1);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name","嘻嘻嘻");
            db.update("sqlite_table",values,"id=?",new String[1001]);
        }
    }

    /**
     * 删除数据的方法
     */
    class DeleteListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DBHelper helper = new DBHelper(SqliteActivity.this, "sqlite_db",null,1);
            SQLiteDatabase db = helper.getReadableDatabase();
            db.delete("sqlite_table","id=?",new String[1002]);
        }
    }


}
