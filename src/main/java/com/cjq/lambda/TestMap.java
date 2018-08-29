package com.cjq.lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jq Chen on 2018/8/17.
 */
public class TestMap {

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1","cjq");
        map.put("2","mjq");

        map.forEach((key,value) -> System.out.println(key + ":" + value));
    }
}
