package com.yujin.event;

/**
 * 事件
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:19
 */
public interface Event {

    void addListener(Listener listener);

    void change();
}
