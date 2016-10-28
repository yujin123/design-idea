package com.yujin.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:31
 */
public class MyTopic implements Subject{

    private String msg;
    private List<Observer> observers = new ArrayList<>();

    public void publish(String msg){
        this.msg = msg;
    }

    @Override
    public void register(Observer observer) {
        if ( null == observer )
            throw new NullPointerException("observer is null");
        if ( !observers.contains(observer) )
            observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void publish() {
        for ( Observer observer : observers ){
            observer.accept(msg);
        }
    }
}
