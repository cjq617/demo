package com.cjq.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jq Chen on 2018/8/10.
 */
public class SomeHandler implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(proxy,args);
        return result;
    }

    public static void main(String[] args) {
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        //Calculator proxy = (Calculator) ProxyFactory.getProxy(ClassLoader);
    }
}
