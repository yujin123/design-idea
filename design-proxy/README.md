## 前言
代理模式顾名思义，就是张三的是有李四代劳，李四就是张三的代理。

## 快速入门

### 静态代理
为了快速理解代理模式，我们开发一个Helle,world！来简单说说明。

上来直接定义一个接口
```java
package com.yujin.proxy.helloworld;

/**
 * 被代理对象接口
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:14
 */
public interface HelloWorld {
    void say();
}
```
接下来写这个类的实现类
```java
package com.yujin.proxy.helloworld;

/**
 * 被代理对象
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:15
 */
public class HelloWorldImpl implements HelloWorld{

    public void say() {
        System.err.println("Hello，World！");
    }
}
```
开始重头戏，怎么定义一个代理类呢？很简单，定义一个代理类实现这个类的接口。

```java
package com.yujin.proxy.helloworld;

/**
 * 代理对象
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:16
 */
public class HelloWorldProxy implements HelloWorld{

    private HelloWorld helloWorld;

    public HelloWorldProxy(HelloWorld helloWorld){
        this.helloWorld = helloWorld;
    }

    public void before(){
        System.err.println("Before.....");
    }

    public void say() {
        before();
        helloWorld.say();
        after();
    }

    public void after(){
        System.err.println("After......");
    }
}
```
启动客户端方法跑这个代理看看效果
```java
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
```
运行结果
```
Before.....
Hello，World！
After......
```

### jdk动态代理
在jdk动态代理中我定义了一个Object target目标对象，通过构造函数进行初始化，在JDKProxy类中我实现了==InvocationHandler==接口和invoke方法
```java
package com.yujin.proxy.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO 用一句话来介绍这个类
 *
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:48
 */
public class JDkProxy implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public JDkProxy(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    public void before(){
        System.err.println("Before.....");
    }

    public void after(){
        System.err.println("After......");
    }
}
```
客户端方法
```java
package com.yujin.proxy.jdk;
import com.yujin.proxy.helloworld.HelloWorld;
import com.yujin.proxy.helloworld.HelloWorldImpl;
import java.lang.reflect.Proxy;

/**
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:54
 */
public class Client {

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorldImpl();

        JDkProxy jdkProxy = new JDkProxy(hello);

        HelloWorld helloProxy = (HelloWorld) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                jdkProxy
        );

        helloProxy.say();
    }
}
```
没错，意思就是，用我写的这个通用的 DynamicProxy 类去包装 HelloWorldImpl 实例，然后再调用 JDK 给我们提供的 Proxy 类的工厂方法 newProxyInstance() 去动态地创建一个 HelloWorld 接口的代理类，最后调用这个代理类的 say() 方法。

观察上面的代码
```java
HelloWorld helloProxy = (HelloWorld) Proxy.newProxyInstance(
            hello.getClass().getClassLoader(),
            hello.getClass().getInterfaces(),
            jdkProxy
    );
```
在每个客户端都会出现，特别的恶心，因此我们需要重构代码。

代码重构
```java
package com.yujin.proxy.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:48
 */
public class JDkProxy implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public JDkProxy(Object target){
        this.target = target;
    }

    /**
     * 获取代理对象
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            this
        );
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    public void before(){
        System.err.println("Before.....");
    }

    public void after(){
        System.err.println("After......");
    }
}
```
```java
package com.yujin.proxy.jdk;
import com.yujin.proxy.helloworld.HelloWorld;
import com.yujin.proxy.helloworld.HelloWorldImpl;
import java.lang.reflect.Proxy;

/**
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 15:54
 */
public class Client {

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorldImpl();

        JDkProxy jdkProxy = new JDkProxy(hello);

        HelloWorld helloWorld = jdkProxy.getProxy();

        helloWorld.say();
    }
}

```

### cglib动态代理
cglib能够代理没有接口的类

```java
package com.yujin.proxy.cglib;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 16:18
 */
public class CglibProxy implements MethodInterceptor{

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o,objects);
        after();
        return obj;
    }

    public void before(){
        System.err.println("Before.....");
    }

    public void after(){
        System.err.println("After......");
    }
}
```
客户端调用
```java
package com.yujin.proxy.cglib;
import com.yujin.proxy.helloworld.HelloWorldImpl;

/**
 * @author yujin
 * @email yujin7@staff.sina.com.cn
 * @create 2016-10-20 16:23
 */
public class Client {

    public static void main(String[] args) {
        CglibProxy cgLibProxy = new CglibProxy();
        HelloWorldImpl hello = cgLibProxy.getProxy(HelloWorldImpl.class);
        hello.say();
    }
}
```