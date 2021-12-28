package com.gql.springcloud.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gql.springcloud.context.CustomGraphQLContext;
import com.gql.springcloud.entities.Account;
import com.gql.springcloud.entities.JWTModel;
import com.gql.springcloud.security.JWTCenter;
import com.gql.springcloud.service.UserService;
import com.gql.springcloud.utils.MD5;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CindyQueryResolver implements GraphQLQueryResolver {

    @Resource
    private UserService userService;

    private ObjectMapper objectMapper;

    public CindyQueryResolver(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("hasAuthority('admin')")
    public String hello(DataFetchingEnvironment environment){
        CustomGraphQLContext context = environment.getContext();
        return context.getHttpServletRequest().getAttribute("userName").toString();
    }
//
    public Account signIn(String account, String password, DataFetchingEnvironment environment) throws JsonProcessingException {
        String passMD5 = MD5.encode(password, account);
        Account user = userService.signIn(account, passMD5);
        log.info(user.getId().toString());
        if(user != null){
            List<String> authorities = new ArrayList<>();
            authorities.add("admin");
            JWTModel jwtModel = new JWTModel();
            jwtModel.setAuthorities(authorities);
            jwtModel.setId(user.getId());
            jwtModel.setUserName(user.getAccount());
            String token = JWTCenter.createToken(objectMapper.writeValueAsString(jwtModel));
//            response.setHeader("access-token", token);
            CustomGraphQLContext context = environment.getContext();
            context.getHttpServletResponse().setHeader("access-token", token);
        }
        return user;
    }

}
