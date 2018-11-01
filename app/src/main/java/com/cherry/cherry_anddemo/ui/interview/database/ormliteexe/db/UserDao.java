package com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.db;

/**
 * Created by yjh on 2018/2/11.
 */


import android.content.Context;


import com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean.UserBean;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedUpdate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 操作User数据表的Dao类，封装这操作User表的所有操作
 * 通过DatabaseHelper类中的方法获取ORMLite内置的DAO类进行数据库中数据的操作
 * <p>
 * 调用dao的create()方法向表中添加数据
 * 调用dao的delete()方法删除表中的数据
 * 调用dao的update()方法修改表中的数据
 * 调用dao的queryForAll()方法查询表中的所有数据
 */

public class UserDao {
    private Context context;

    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<UserBean,Integer> dao;

    public UserDao(Context context){
        this.context = context;
        this.dao = DataBaseHelper.getInstance(context).getDao(UserBean.class);
    }

    public void insert(UserBean data){
        try {
            dao.create((Collection<UserBean>) data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(UserBean data){
        try {
            dao.delete((PreparedDelete<UserBean>) data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(UserBean data){
        try {
            dao.update((PreparedUpdate<UserBean>) data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserBean> selectAll(){
        List<UserBean> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserBean queryById(int id){
        UserBean user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }



}
