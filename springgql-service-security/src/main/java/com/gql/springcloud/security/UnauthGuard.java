package com.gql.springcloud.security;

import com.gql.springcloud.entities.CommonResult;
import com.gql.springcloud.enums.ResEnum;
import com.gql.springcloud.utils.SetResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class UnauthGuard implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SetResponse.setRes(response, new CommonResult(ResEnum.UNAUTH.getCode(), ResEnum.UNAUTH.getDes()));
    }
}
