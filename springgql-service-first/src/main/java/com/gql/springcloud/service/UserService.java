package com.gql.springcloud.service;

import com.gql.springcloud.entities.Account;

public interface UserService {
    public int signUp(Account user);

    public Account signIn(String account, String password);

    public int changePassword(String account, String password);
}
