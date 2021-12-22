package com.gql.springcloud.components;

import com.gql.springcloud.entities.User;
import com.gql.springcloud.service.UserService;
import com.gql.springcloud.utils.MD5;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CindyQueryResolver implements GraphQLQueryResolver {

    @Resource
    private UserService userService;

    public String hello(){
        return "hello world";
    }

    public User signIn(String account, String password){
        String passMD5 = MD5.encode(password, account);
        User user = userService.signIn(account, passMD5);
        return user;
    }

}
