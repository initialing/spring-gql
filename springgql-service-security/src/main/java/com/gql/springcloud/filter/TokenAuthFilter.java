package com.gql.springcloud.filter;

import com.gql.springcloud.entities.JWTModel;
import com.gql.springcloud.security.JWTCenter;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Slf4j
public class TokenAuthFilter extends BasicAuthenticationFilter {


    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request, response);
        if(authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        if(token != null){
            boolean effect = false;
            try {
                JWTCenter.parseJwt(token);
                effect = true;
            } catch (ExpiredJwtException e){
                if(e.getMessage().contains("Allowed clock skew")){
                    Date et = e.getClaims().getExpiration();
                    long exMillis = et.getTime();
                    long nowMillis = System.currentTimeMillis();
                    log.info("******** 过期时间"+exMillis+"\t"+nowMillis);
                    if(nowMillis - exMillis < 1000L * 60L * 60L * 24L){
                        String subject = e.getClaims().getSubject();
                        try {

                            token = JWTCenter.createToken(subject);
                            response.setHeader("access-token", token);
                            effect = true;
                        } catch (Exception ne){
                            log.error("******** 刷新token失败"+ne.getMessage(),ne);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(effect){
                JWTModel jwtModel = JWTCenter.getModel(token);
                System.out.print(jwtModel.getId());
                request.setAttribute("userId", jwtModel.getId());
                request.setAttribute("userName", jwtModel.getUserName());
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for(String p : jwtModel.getAuthorities()){
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(p);
                    authorities.add(authority);
                }
                return new UsernamePasswordAuthenticationToken(jwtModel, token, authorities);
            }
        }
        return null;
    }
}
