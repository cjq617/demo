package com.cjq.annotation;

public class Child extends Person {
    @Override
    public String name() {
        return super.name();
    }

    @Override
    int age() {
        return super.age();
    }
}
