package com.gql.springcloud.controller;

import com.gql.springcloud.entities.User;
import com.gql.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class Strokes {

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return configInfo;
    }

    @PostMapping("/first/signup")
    public User signUp(@RequestBody User user){
        userService.signUp(user);
        return user;
    }
}
