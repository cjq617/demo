package com.cjq.aop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jq Chen on 2018/8/16.
 */
@Component
public class UserService {

    @Autowired
    private LogService logService;

    @Autowired
    private CacheService cacheService;

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public void setCacheService(CacheService cacheService) {

        this.cacheService = cacheService;
    }

    public Object getUserById(String id) {
        Object obj = null;
        cacheService.getFromCache(id);
        obj = new Object();
        cacheService.setCache(id, obj);
        logService.info("get success");
        return obj;
    }

    @Test
    public void testAnn() {
        String id = "123";
        Object obj = getUserById(id);
        System.out.println(obj);
    }
}
