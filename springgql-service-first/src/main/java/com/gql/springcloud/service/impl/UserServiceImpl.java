package com.gql.springcloud.service.impl;

import com.gql.springcloud.dao.UserDao;
import com.gql.springcloud.entities.Account;
import com.gql.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int signUp(Account user){
        return userDao.signUp(user);
    }

    @Override
    public Account signIn(String account, String password){
        return userDao.signIn(account, password);
    }

    @Override
    public int changePassword(String account, String password){
        return userDao.changePassword(account, password);
    }
}
