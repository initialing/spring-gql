package com.gql.springcloud.filter;

import com.gql.springcloud.entities.JWTModel;
import com.gql.springcloud.security.JWTCenter;
import org.springframework.beans.factory.annotation.Autowired;
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

public class TokenAuthFilter extends BasicAuthenticationFilter {

    @Autowired
    private JWTCenter jwtCenter;

    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
        if(authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            JWTModel jwtModel = jwtCenter.getModel(token);
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
        return null;
    }
}
