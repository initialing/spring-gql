package com.gql.springcloud.components;

import com.gql.springcloud.entities.CommonResult;
import com.gql.springcloud.enums.ResEnum;
import com.gql.springcloud.service.UserService;
import com.gql.springcloud.utils.MD5;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LaraMutationResolver implements GraphQLMutationResolver {

    @Resource
    private UserService userService;

    public CommonResult changePassword(String account, String password){
        String passMD5 = MD5.encode(password, account);
        userService.changePassword(account, passMD5);
        return new CommonResult(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getDes());
    }

}
