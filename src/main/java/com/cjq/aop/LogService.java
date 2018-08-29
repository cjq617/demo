package com.cjq.aop;
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
}
