package com.hblolj.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther Ori
 * @Date 2019/1/8 21 39
 * @Description 媒婆
 */
public class MeiPo implements InvocationHandler {

    private Person person;

    // 获取被代理人的资料
    public Object getInstance(Person target) throws Exception{
        this.person = target;
        Class<? extends Person> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆，被代理性别: " + person.getSex() + " 需要找一个异性");
        System.out.println("进行海选");
        System.out.println("-------------------------------------");
        method.invoke(this.person, args);
        System.out.println("-------------------------------------");
        System.out.println("如果合适的话，就扯证");
        return null;
    }
}
