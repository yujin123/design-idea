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
