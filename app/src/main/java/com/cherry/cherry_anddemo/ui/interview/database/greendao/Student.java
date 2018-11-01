package com.cherry.cherry_anddemo.ui.interview.database.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id
    private Long stuId;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStuId() {
        return this.stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    @Generated(hash = 2100816406)
    public Student(Long stuId, String name) {
        this.stuId = stuId;
        this.name = name;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }
}
