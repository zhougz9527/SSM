package com.ssm.service;

import com.ssm.entity.User;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2019/4/17
 * @Time: 18:13
 */
public interface UserService {

    Optional<User> saveUser(User user);

    Optional<User> findByUserName(String username);

}
