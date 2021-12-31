package com.gql.springcloud.config;

import com.gql.springcloud.filter.TokenAuthFilter;
import com.gql.springcloud.security.UnauthAccessDenied;
import com.gql.springcloud.security.UnauthGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {


    private UnauthGuard unauthGuard;

    private UnauthAccessDenied unauthAccessDenied;

    public JWTSecurityConfig(UnauthGuard unauthGuard, UnauthAccessDenied unauthAccessDenied){
        this.unauthGuard = unauthGuard;
        this.unauthAccessDenied = unauthAccessDenied;
    }


    @Bean
    public TokenAuthFilter tokenAuthFilter() throws Exception {
        TokenAuthFilter tokenAuthFilter = new TokenAuthFilter(authenticationManager());
        return tokenAuthFilter;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 拦截规则
                .and()
                .authorizeRequests()
                .antMatchers("/**/graphql", "/first/**").permitAll()
                .anyRequest().authenticated()
                // 未授权处理
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthGuard)
                .accessDeniedHandler(unauthAccessDenied)

                .and()
                .addFilter(tokenAuthFilter())
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        webSecurity.ignoring().antMatchers("/ignore");
    }
}
