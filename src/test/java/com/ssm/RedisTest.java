package com.ssm;


import com.alibaba.fastjson.JSON;
import com.ssm.utils.RedisUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * @Author: Think
 * @Date: 2019/3/27
 * @Time: 18:32
 */
public class RedisTest extends BaseTest {

    @Test
    public void redisTest() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jinmu");
        map.put("gender", "男");
        map.put("age", 18);
        redisUtil.set("map", JSON.toJSONString(map));
    }

    @Test
    public void jsonTest() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        System.out.println(JSON.toJSONString(stringList));
    }

}
