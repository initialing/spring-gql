package com.gql.springcloud.controller;

import com.gql.springcloud.entities.Account;
import com.gql.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class Strokes {

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private UserService userService;

    @GetMapping("/first/test")
    @PreAuthorize("hasAuthority('admin')")
    public String test(HttpServletRequest request){
        return configInfo + request.getAttribute("userName").toString();
    }

    @PostMapping("/first/signup")
    public Account signUp(@RequestBody Account user){
        userService.signUp(user);
        return user;
    }
}
