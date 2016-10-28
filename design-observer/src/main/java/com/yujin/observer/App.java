package com.yujin.observer;

/**
 * 测试类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:40
 */
public class App {

    public static void main(String[] args) {

        MyTopic topic = new MyTopic();

        Observer observer1 = new TopicSubscriber("张三1");
        Observer observer2 = new TopicSubscriber("张三2");
        Observer observer3 = new TopicSubscriber("张三3");
        Observer observer4 = new TopicSubscriber("张三4");
        Observer observer5 = new TopicSubscriber("张三5");

        topic.publish("大家注意，带薪周二放假一天。。");

        topic.register(observer1);
        topic.register(observer2);
        topic.register(observer3);
        topic.register(observer4);
        topic.register(observer5);

        topic.publish();
    }
}
