package com.gql.springcloud.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gql.springcloud.entities.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetResponse {

    public static void setRes(HttpServletResponse response, CommonResult cr){
        ObjectMapper om = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try{
            om.writeValue(response.getWriter(), cr);
        } catch (IOException e){
            System.out.print(e.getMessage());
        }
    }
}
