package com.ssm;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.bo.Student;
import com.ssm.bo.User;
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
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
          studentList.add(new Student("小明" + i, "数学", 99.99));
        }
        User user = new User("jinmu", "男", 18, studentList);
        redisUtil.set("user", JSON.toJSONString(user));
        System.out.println(redisUtil.get("user"));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jinmu");
        map.put("gender", "男");
        map.put("age", 18);
        redisUtil.set("map", JSON.toJSONString(map));
    }

}
