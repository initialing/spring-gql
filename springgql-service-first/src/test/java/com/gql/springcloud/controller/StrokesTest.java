package com.gql.springcloud.controller;

import com.gql.springcloud.entities.CommonResult;
import com.gql.springcloud.enums.ResEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StrokesTest {


    @Test
    void helloTest(@Autowired WebTestClient webTestClient) throws Exception{
        webTestClient
                .get().uri("/first/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("hello");
    }

    @Test
    void testTest(@Autowired WebTestClient webTestClient) throws Exception{
        webTestClient
                .get().uri("/first/test")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommonResult.class)
                .isEqualTo(new CommonResult(ResEnum.UNAUTH.getCode(), ResEnum.UNAUTH.getDes()));
    }

    @Test
    void testAdminTest(@Autowired WebTestClient webTestClient) throws Exception{
        webTestClient
                .get().uri("/first/test")
                .header("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJ1aWQiOiJzcHJpbmdHcWwiLCJzdWIiOiJ7XCJpZFwiOjEsXCJ1c2VyTmFtZVwiOlwienF0XCIsXCJhdXRob3JpdGllc1wiOltcImFkbWluXCJdfSIsInVzZXJfbmFtZSI6ImluaXRpYWxpbmciLCJleHAiOjE2NDA5ODM4MDIsImlhdCI6MTY0MDk0MDYwMiwianRpIjoiaWQifQ.kCbuq-_mZmlh7k7NZQxuuA88uMXnnxVSvnPv9hC_jH3-89hQ3W4UZg5qFVtPGCv2cF-8LHtcUQk6VYb_TA53QQ")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommonResult.class)
                .isEqualTo(new CommonResult(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getDes(), "this is spring gql first zqt"));
    }
}
