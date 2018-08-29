package com.cjq.aop;

import org.junit.Test;

/**
 * Created by jq Chen on 2018/8/22.
 */
public class ClientTest {

    @Test
    public void test() {
        LogService logService = new LogService();
        logService.info("test");
    }
}
