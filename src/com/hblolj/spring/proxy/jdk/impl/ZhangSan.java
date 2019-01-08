package com.hblolj.spring.proxy.jdk.impl;

import com.hblolj.spring.proxy.jdk.Person;

/**
 * @auther Ori
 * @Date 2019/1/8 21 33
 * @Description
 */
public class ZhangSan implements Person {

    private String sex = "男";
    private String name = "张三";

    @Override
    public void findLove() {
        System.out.println("我叫" + name + " 性别: " + sex + " 择偶要求: ");
        System.out.println("肤白");
        System.out.println("貌美");
        System.out.println("胸大");
        System.out.println("腿长");
        System.out.println("温柔");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
