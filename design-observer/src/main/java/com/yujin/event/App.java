package com.yujin.event;

/**
 * 测试事件监听类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:22
 */
public class App {

    public static void main(String[] args) {
        Event myEvent = new MyEvent();
        myEvent.addListener(new Listener() {
            @Override
            public void eventChange() {
                System.err.println("aaaaaaaaaaaa");
            }
        });

        myEvent.change();
    }
}
