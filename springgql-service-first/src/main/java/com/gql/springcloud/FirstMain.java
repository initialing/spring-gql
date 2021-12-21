package com.gql.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FirstMain {
    public static void main(String [] args){
        SpringApplication.run(FirstMain.class, args);
    }
}
