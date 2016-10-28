package com.yujin.proxy.jdk;

import com.yujin.proxy.helloworld.HelloWorld;
import com.yujin.proxy.helloworld.HelloWorldImpl;

import java.lang.reflect.Proxy;

/**
 * TODO 用一句话来介绍这个类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:54
 */
public class Client {

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorldImpl();

        JDkProxy jdkProxy = new JDkProxy(hello);

        HelloWorld helloWorld = jdkProxy.getProxy();

        helloWorld.say();
    }
}
