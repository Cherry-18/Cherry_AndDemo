package com.cherry.cherry_anddemo.ui.interview.database.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 不需要写 getset方法，只要写上属性，然后  点击build -- make project  自动生成get set 和 dao 文件
 *
 schema：告知GreenDao当前实体属于哪个schema
 active：标记一个实体处于活动状态，活动实体有更新、删除和刷新方法  无论是更新生成都刷新
 nameInDb：在数据中使用的别名，默认使用的是实体的类名
 indexes：定义索引，可以跨越多个列
 createInDb：标记创建数据库表**  是否创建表，默认为true,false时不创建
 */

@Entity
public class User {
    /**
     基础属性注解
         @Id :主键 Long型，可以通过@Id(autoincrement = true)设置自增长
         @Property ：设置一个非默认关系映射所对应的列名，默认是的使用字段名举例：
         @Property (nameInDb="name")
         @NotNul ：设置数据库表当前列不能为空
         @Transient ：添加次标记之后不会生成数据库表的列

     索引注解
         @Index ：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
         @Unique ：向数据库列添加了一个唯一的约束

     关系注解
         @ToOne ：定义与另一个实体（一个实体对象）的关系
         @ToMany ：定义与多个实体对象的关系
     */

    @Id(autoincrement = true)//id自增长
    private Long id;

    @Property(nameInDb = "Name")//重命名字段名
    private String name;

    @Property(nameInDb = "Age")
    private String age;

    @Property(nameInDb = "Sex")
    private String sex;

    @Transient //不放入持久层(数据库)
    private String phoneNo;

    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 596426048)
    public User(Long id, String name, String age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
