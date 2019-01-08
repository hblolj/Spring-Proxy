package com.hblolj.spring.custom;

import com.hblolj.spring.proxy.jdk.Person;

import java.lang.reflect.Method;

/**
 * @auther Ori
 * @Date 2019/1/8 22 37
 * @Description
 */
public class MyMeiPo implements MyInvocationHandler{

    private Person target;

    // 获取被代理人的资料
    public Object getInstance(Person target) throws Exception{
        this.target = target;
        System.out.println("被代理的对象: " + target.getClass().getSimpleName());
        Class<? extends Person> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆，被代理性别: " + target.getSex() + " 需要找一个异性");
        System.out.println("进行海选");
        System.out.println("-------------------------------------");
        method.invoke(this.target, args);
        System.out.println("-------------------------------------");
        System.out.println("如果合适的话，就扯证");
        return null;
    }
}
