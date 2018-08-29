package com.cjq.guava;

import com.google.common.base.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jq Chen on 2018/8/22.
 */
public class StringDemo {
    @Test
    public void test1() {
        //null跟""判断
        System.out.println(Strings.isNullOrEmpty(""));
    }

    @Test
    public void test2() {
        //获得两个字符串相同的前缀或者后缀
        //Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }

    @Test
    public void test3() {
        //Strings的padStart和padEnd方法来补全字符串
        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    @Test
    public void test4() {
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()//忽略空字符串
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }

        System.out.println("=============================");
        List<String> list1 = Splitter.on(';')
                .trimResults()//移除结果字符串的前导空白和尾部空白
                .omitEmptyStrings()//从结果中自动忽略空字符串
                .splitToList("foo;bar;; ; qux;");
        System.out.println(list1);

        System.out.println("============trimResults=============");
        //先分割，后去除前后特殊字符
        Iterable<String> list2 = Splitter.on(',').trimResults(CharMatcher.is('_')).split("_a ,_b_ ,c__");
        for (String s : list2) {
            System.out.println(s);
        }

    }

    @Test
    public void test5() {
        //二次拆分首先是使用onPattern做第一次的拆分，然后再通过withKeyValueSeperator('')方法做第二次的拆分。
        String toSplitString = "a=b;c=d,e=f";
        Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String,String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }
    }

    @Test
    public void test6() {
        String joinResult = Joiner.on(" ").join(new String[]{"hello","world"});
        System.out.println(joinResult);

        joinResult = Joiner.on("--").join(Arrays.asList("chen,jin,qiang"));//只能是,
        System.out.println(joinResult);

        joinResult = Joiner.on(";").join("c","j","q");
        System.out.println(joinResult);
    }

    @Test
    public void test7() {
        //二次合并
        Map<String,String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }

    @Test
    public void test8() {
        String theDigits = CharMatcher.DIGIT.retainFrom("今天是2016年9月16日");
        System.out.println(theDigits);
    }

    @Test
    public void test9() {
        String lowerHyphen = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "userName");
        System.out.println(lowerHyphen);

        String lowerUnderscore = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "userName");
        System.out.println(lowerUnderscore);

        String upperCamel = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "userName");
        System.out.println(upperCamel);

        String upperUnderscore = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "userName");
        System.out.println(upperUnderscore);
    }
}
