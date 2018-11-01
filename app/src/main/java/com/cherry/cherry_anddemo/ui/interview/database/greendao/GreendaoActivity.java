package com.cherry.cherry_anddemo.ui.interview.database.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cherry.cherry_anddemo.CherryApplication;
import com.cherry.cherry_anddemo.R;
import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.StudentDao;
import com.cherry.cherry_anddemo.ui.interview.database.greendao.gen.UserDao;

import java.util.List;

public class GreendaoActivity extends AppCompatActivity {

    private UserDao dao;
    private StudentDao studentDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cherry_activity_greendao);
        dao = CherryApplication.getApplication().getDaoSession().getUserDao();
    }

    /**
     * 增
     */
    private void insertData() {
        String name = "";
        String age = "";
        String sex = "";
        User insertData = new User(null, name, age, sex);
        dao.insert(insertData);
    }

    /**
     * 插入用户集合
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        dao.insertInTx(users);
    }

    /**
     * 删
     */
    private void deleteData(User user){
//        Long userId = 0L ;
//        dao.deleteByKey(userId);
        dao.delete(user);
    }

    //改

    private void updateData(int i) {
        User updateData = new User(1l, "", "", "");
        dao.update(updateData);
    }

    //查

    private void queryData() {
        List<User> users = dao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ",";
        }
        Toast.makeText(this, "查询全部数据==>" + userName, Toast.LENGTH_SHORT).show();
    }
}
