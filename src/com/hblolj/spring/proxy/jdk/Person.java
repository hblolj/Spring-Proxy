package com.hblolj.spring.proxy.jdk;

/**
 * @auther Ori
 * @Date 2019/1/8 21 32
 * @Description
 */
public interface Person {

    /**
     * 相亲
     */
    void findLove() throws Throwable;

    String getSex() throws Throwable;

    String getName() throws Throwable;
}
