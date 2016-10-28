package com.yujin.proxy.helloworld;

/**
 * 客户端
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:19
 */
public class Client {

    public static void main(String[] args) {
        HelloWorldProxy pwp = new HelloWorldProxy(new HelloWorldImpl());
        pwp.say();
    }
}
