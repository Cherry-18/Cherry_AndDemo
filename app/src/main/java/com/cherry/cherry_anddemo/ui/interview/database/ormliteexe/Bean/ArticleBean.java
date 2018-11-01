package com.cherry.cherry_anddemo.ui.interview.database.ormliteexe.Bean;

/**
 * Created by yjh on 2018/2/9.
 */


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ArticleBean实体类，存储article数据表中的数据
 * 数据库中的article表和user表是关联的，因此我们需要在article表中配置外键
 * <p>
 * foreignColumnName：外键约束指向的类中的属性名
 * foreign：当前字段是否是外键
 * foreignAutoRefresh：如果这个属性设置为true，在关联查询的时候就不需要再调用refresh()方法了
 */

@DatabaseTable(tableName = "article")
public class ArticleBean {
    @DatabaseField(generatedId = true, columnName = "id", useGetSet = true)
    private int id;

    @DatabaseField(columnName = "title", useGetSet = true, canBeNull = false, unique = true)
    private String title;

    @DatabaseField(columnName = "content", useGetSet = true)
    private String content;

    @DatabaseField(columnName = "user_id", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private UserBean user_id;

    public ArticleBean(){

    }

    public ArticleBean(String title, String content, UserBean user){
        this.title = title;
        this.content = content;
        this.user_id = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser_id() {
        return user_id;
    }

    public void setUser_id(UserBean user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
