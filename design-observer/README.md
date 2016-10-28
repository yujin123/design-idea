## 观察者模式
### 1.概述
    有时被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。

### 2. 类图

### 3. 涉及到的对象
抽象主题（Subject）：它把所有观察者对象的引用保存到一个聚集里，每个主题都可以有任何数量的观察者。抽象主题提供一个接口，可以增加和删除观察者对象。
具体主题（MyTopic）：将有关状态存入具体观察者对象；在具体主题内部状态改变时，给所有登记过的观察者发出通知。
抽象观察者（Observer）：为所有的具体观察者定义一个接口，在得到主题通知时更新自己。
具体观察者（TopicSubscriber）：实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题状态协调。

### 4. 涉及的代码
抽象的主题类
```java
package com.yujin.observer;

/**
 * 抽象主题类
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
```

主题类
```java
package com.yujin.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题类
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
```
抽象观察者
```java
package com.yujin.observer;

/**
 * 观察者类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:29
 */
public interface Observer {

    void accept(String msg);
}
```
具体观察者（主题的订阅者）
```java
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
```
测试
```java
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
```

### 观察者模式的应用

#### 1. 事件监听器
（1）定义时间监听接口
```java
package com.yujin.event;

/**
 * 事件监听接口
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-27 19:10
 */
public interface Listener {

    void eventChange();
}
```
（2）定义抽象的事件类
```java
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
```
（3）定义具体的事件类
```java
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
```
（4）测试类
```java
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
```

#### 2. 回调机制



