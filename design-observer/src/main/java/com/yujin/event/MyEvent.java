package com.yujin.event;

/**
 * 事件
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:20
 */
public class MyEvent implements Event{

    private Listener listener;

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public void change(){
        listener.eventChange();
    }
}
