package com.yujin.event;

/**
 * 时间监听器（观察者）
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:11
 */
public class MyEventListener implements Listener {

    @Override
    public void eventChange() {
        System.err.println("事件发生改变....");
    }
}
