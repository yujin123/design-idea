package com.yujin.observer;

/**
 * 主题订阅者
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:36
 */
public class TopicSubscriber implements Observer{

    private String name;

    public TopicSubscriber(String name){
        this.name = name;
    }

    @Override
    public void accept(String msg) {
        System.err.println(name+":============>>>收到的消息内容是："+msg);
    }
}
