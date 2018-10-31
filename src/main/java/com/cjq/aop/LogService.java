package com.cjq.aop;
import com.cjq.annotation.sql.User;
import org.springframework.stereotype.Component;

/**
 * Created by jq Chen on 2018/8/16.
 */
@Component
public class LogService {

    public void info(String message) {
        System.out.println(message);
    }

    public void error(String message) {
        System.out.println(message);
    }

    @DoSomething(key="cjq", cacheName = "11")
    public void changeParam(User user) {
        System.out.println("method:" + user.getName());
    }

    @DoSomething(key="cjq", cacheName = "22")
    public User changeReturn(User user) {
        System.out.println("return:" + user.getName());
        return user;
    }
}
