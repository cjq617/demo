package com.cjq.aop;

import com.cjq.annotation.sql.User;
import com.cjq.sprinttest.BaseJunit4Test;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestAop extends BaseJunit4Test {

    LogService logService;

    @Resource
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Test
    public void test() {
        //LogService logService = new LogService();
        //logService.info("11");
        User user = new User();
        user.setAge(20);
        user.setName("cjq");
        //logService.changeParam(user);
        System.out.println("result:" + logService.changeReturn(user).getName());
    }
}
