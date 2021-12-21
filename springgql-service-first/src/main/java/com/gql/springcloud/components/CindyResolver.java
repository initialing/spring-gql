package com.gql.springcloud.components;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class CindyResolver implements GraphQLQueryResolver {

    public String hello(){
        return "hello world";
    }

}
