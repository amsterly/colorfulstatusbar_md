package com.zhy.colorfulstatusbar;

/**
 * Created by Administrator on 2016/3/21.
 */
public class Student {
    private int sex;
    private String name;

    public int getSex() {
        return sex;
    }

    public Student(int sex, String name) {
        this.sex = sex;
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
