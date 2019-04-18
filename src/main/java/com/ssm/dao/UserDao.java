package com.ssm.dao;

import com.ssm.entity.User;

/**
 * @Author: Think
 * @Date: 2019/4/15
 * @Time: 18:39
 */
public interface UserDao {

    /**
     * 通过id查看User
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 新增User
     * @param user
     * @return
     */
    int save(User user);



}
