package com.cjq.sprinttest;

import com.cjq.lambda.Person;
import com.google.gson.Gson;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by jq Chen on 2018/9/3.
 */
public class test extends BaseJunit4Test {

    @Resource
    private Gson gson;

    @Test
    public void test() {
        Person person = new Person("f", "n", "job", "m", 18, 5000);
        System.out.println(gson.toJson(person));

    }
}
