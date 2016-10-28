package com.yujin.proxy.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
