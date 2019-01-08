package com.hblolj.spring.proxy.jdk;

import com.hblolj.spring.proxy.jdk.impl.ZhangSan;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @auther Ori
 * @Date 2019/1/8 21 35
 * @Description
 */
public class TestFindLove {

    public static void main(String[] args) {
//        new ZhangSan().findLove();
        try {
            Person p = (Person) new MeiPo().getInstance(new ZhangSan());
//            p.findLove();
            p.getSex();

            // 获取字节码内容
//            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
//            FileOutputStream fos = new FileOutputStream("D:/Spring/src/com/hblolj/spring/proxy/$Proxy0.class");
//            fos.write(data);
//            fos.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
