package com.cjq.aop;

import org.springframework.stereotype.Component;

/**
 * Created by jq Chen on 2018/8/16.
 */
@Component
public class CacheService {

    public String getFromCache(String id) {
        System.out.println("get object from cache:" + id);
        return "";
    }

    public void setCache(String key, Object obj) {
        System.out.println("set cache:{" + key + ":" + obj + "}");
    }
}
