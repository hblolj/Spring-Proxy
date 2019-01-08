package com.hblolj.spring.custom;

import com.hblolj.spring.proxy.jdk.Person;
import com.hblolj.spring.proxy.jdk.impl.ZhangSan;

/**
 * @auther Ori
 * @Date 2019/1/8 22 38
 * @Description
 */
public class TestMyProxy {

    public static void main(String[] args) {
        try {
            Person p = (Person) new MyMeiPo().getInstance(new ZhangSan());
            System.out.println("生成的代理对象: " + p.getClass().getSimpleName());
            p.findLove();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
