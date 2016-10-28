package com.yujin.proxy.helloworld;

/**
 * 被代理对象
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:15
 */
public class HelloWorldImpl implements HelloWorld{

    public void say() {
        System.err.println("Hello，World！");
    }
}
