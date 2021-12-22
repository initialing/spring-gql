package com.gql.springcloud.service;

import com.gql.springcloud.entities.User;

public interface UserService {
    public int signUp(User user);

    public User signIn(String account, String password);

    public int changePassword(String account, String password);
}
