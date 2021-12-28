package com.gql.springcloud.security;

import com.gql.springcloud.entities.CommonResult;
import com.gql.springcloud.enums.ResEnum;
import com.gql.springcloud.utils.SetResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class UnauthAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        SetResponse.setRes(response, new CommonResult(ResEnum.UNAUTH.getCode(), accessDeniedException.getMessage()));
    }
}
