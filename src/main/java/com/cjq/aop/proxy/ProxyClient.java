package com.cjq.aop.proxy;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by jq Chen on 2018/8/22.
 */
public class ProxyClient {

    @Test
    public void test() {
        Person person = new ManProxy(new Man());
        person.sign();
        person.jump();
    }

    @Test
    public void testDynamic() throws Exception {
        //获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(Person.class.getClassLoader(),Person.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        Person p = (Person) constructor.newInstance(new DynamicProxy(new Man()));
        p.sign();

        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),//加载接口的类加载器
                new Class[]{Person.class}, //一组接口
                new DynamicProxy(new Man()));//自定义的InvocationHandler
        person.sign();
        person.jump();
    }
}
