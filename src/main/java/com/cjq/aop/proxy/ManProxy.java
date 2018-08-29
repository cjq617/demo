package com.cjq.aop.proxy;

/**
 * 静态代理
 * Created by jq Chen on 2018/8/22.
 */
public class ManProxy implements Person {
    private Person person;

    public ManProxy(Person person) {
        this.person = person;
    }

    @Override
    public void jump() {
        System.out.println("-------before-------");
        person.jump();
        System.out.println("--------after-------");
    }

    @Override
    public void sign() {
        System.out.println("-------before-------");
        person.sign();
        System.out.println("--------after-------");
    }
}
