package com.cjq.aop.proxy;

/**
 * Created by jq Chen on 2018/8/22.
 */
public class Man implements Person {
    @Override
    public void sign() {
        System.out.println("man sign");
    }

    @Override
    public void jump() {
        System.out.println("man jump");
    }
}
