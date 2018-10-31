package com.cjq.aop;

import com.cjq.annotation.sql.User;
import org.aspectj.apache.bcel.classfile.Module;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by jq Chen on 2018/8/22.
 */
@Component
@Aspect
public class DemoAspect {

    @Pointcut("execution(* com.cjq.aop.LogService.*(..)) && @annotation(DoSomething)")
    public void doCut() {

    }

    @Pointcut("execution(* com.cjq.aop.LogService.info(..)) || execution(* com.cjq.aop.LogService.changeParam(..))")
    public void orCut() {

    }

    @Before("orCut()")
    public void before(JoinPoint point) {
        Object obj = point.getArgs()[0];
        System.out.println("arg:" + obj);
        if(obj instanceof User) {
            User user = (User)obj;
            System.out.println(user.getName());
            user.setName("aaa");
        }
        System.out.println("=======before======");
    }

    @After("doCut()")
    public void after() {
        System.out.println("======after========");
    }

    @Around("doCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        System.out.println("=====arount before=====");
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("=====around after=====");
        return result;
    }

    @AfterReturning(value = "doCut()", returning = "result")
    public void afterReturn(Object result) {
        System.out.println("=====afterReturn=====" + result);
        if(result instanceof User) {
            User user = (User)result;
            user.setName("wqx");
        }
        //return result;
    }
}
