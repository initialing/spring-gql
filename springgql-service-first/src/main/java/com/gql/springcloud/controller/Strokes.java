package com.gql.springcloud.controller;

import com.gql.springcloud.entities.Account;
import com.gql.springcloud.entities.CommonResult;
import com.gql.springcloud.enums.ResEnum;
import com.gql.springcloud.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RefreshScope
public class Strokes {

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private UserService userService;

    @GetMapping("/first/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/first/test")
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult test(HttpServletRequest request){
        return new CommonResult(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getDes(), configInfo + " " + request.getAttribute("userName").toString());
    }

    @PostMapping("/first/signup")
    public CommonResult signUp(@RequestBody Account user){
        userService.signUp(user);
        return new CommonResult(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getDes());
    }
}
