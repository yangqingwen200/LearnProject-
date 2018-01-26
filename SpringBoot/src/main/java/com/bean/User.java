package com.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {

    private Integer id;
    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date born;
    @JSONField(serialize = false)
    private Integer age;

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
