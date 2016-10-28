package com.yujin.observer;

/**
 * TODO 用一句话来介绍这个类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:28
 */
public interface Subject {

    void register(Observer observer);

    void unregister(Observer observer);

    void publish();
}
