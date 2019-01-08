package com.hblolj.spring.custom;

import java.lang.reflect.Method;

/**
 * @auther Ori
 * @Date 2019/1/8 22 33
 * @Description
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
