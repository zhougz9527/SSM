package com.ssm.service.impl;

import com.ssm.dao.UserDao;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.utils.CommonUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2019/4/17
 * @Time: 18:14
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Optional<User> saveUser(User user) {
        if (CommonUtil.notEmpty(user)) {
            String bcryptPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(bcryptPassword);
            int id = userDao.save(user);
            user.setId(id);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(userDao.findByUserName(username));
    }
}
