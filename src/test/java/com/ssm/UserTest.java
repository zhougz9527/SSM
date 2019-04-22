package com.ssm;

import com.ssm.dao.UserDao;
import com.ssm.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Think
 * @Date: 2019/4/17
 * @Time: 10:59
 */
public class UserTest extends BaseTest {

    @Autowired
    UserDao userDao;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("金木");
        user.setAvatar("https://www.google.com");
        user.setPassword("123456");
        int id = userDao.save(user);
        System.out.println("---------" + id + "----------");
    }

    @Test
    public void test() {
        System.out.println("------------" + "http://///".startsWith("http") + "-----------");
    }

}
