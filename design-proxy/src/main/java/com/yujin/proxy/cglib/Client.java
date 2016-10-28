package com.yujin.proxy.cglib;

import com.yujin.proxy.helloworld.HelloWorld;
import com.yujin.proxy.helloworld.HelloWorldImpl;

/**
 * TODO 用一句话来介绍这个类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 16:23
 */
public class Client {

    public static void main(String[] args) {
        CglibProxy cgLibProxy = new CglibProxy();
        HelloWorld hello = cgLibProxy.getProxy(HelloWorldImpl.class);
        hello.say();
    }
}
