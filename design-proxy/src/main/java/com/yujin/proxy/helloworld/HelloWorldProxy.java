package com.yujin.proxy.helloworld;

/**
 * 代理对象
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:16
 */
public class HelloWorldProxy implements HelloWorld{

    private HelloWorld helloWorld;

    public HelloWorldProxy(HelloWorld helloWorld){
        this.helloWorld = helloWorld;
    }

    public void before(){
        System.err.println("Before.....");
    }

    public void say() {
        before();
        helloWorld.say();
        after();
    }

    public void after(){
        System.err.println("After......");
    }
}
