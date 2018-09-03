package com.cjq.sprinttest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于spring配置文件的测试基类
 * Created by jq Chen on 2018/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class BaseJunit4Test {
}
