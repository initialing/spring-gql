package com.gql.springcloud.service.impl;

import com.gql.springcloud.dao.UserDao;
import com.gql.springcloud.entities.User;
import com.gql.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int signUp(User user){
        return userDao.signUp(user);
    }
}
