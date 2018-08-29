package com.cjq.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by jq Chen on 2018/8/22.
 */
@Component
@Aspect
public class DemoAspect {

    @Pointcut("execution(* com.cjq.demo.*.*(..))")
    public void doCut() {

    }

    @Before("doCut()")
    public void before() {
        System.out.println("=======before======");
    }
}
