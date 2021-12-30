package com.gql.springcloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gql.springcloud.entities.JWTModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.CompressionCodecs;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTCenter {

    private static long expireTime = 12L * 60L * 60L * 1000;

    private static String key = "gz8wk72mo25bglgooa1a97cnnojcjsj";

    private static ObjectMapper objectMapper;

    public JWTCenter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    public static String createToken(String subject){

        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", "springGql");
        claims.put("user_name", "initialing");

        String token = Jwts.builder()
                            .setClaims(claims)
                            .setId("id")
                            .setSubject(subject)
                            .setIssuedAt(new Date())
                            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                            .signWith(SignatureAlgorithm.HS512, key)
                            .compact();
        return token;
    }

    // 解析 token 成 Claim 方便后续操作
    public static Claims parseJwt(String token) throws Exception {
        Claims claims = Jwts.parser()
                            .setSigningKey(key)
                            .parseClaimsJws(token)
                            .getBody();
        return claims;
    }

    // 获取 token body 内容
    public static JWTModel getModel(String token){
        try {
            Claims claims = parseJwt(token);
            String subject = claims.getSubject();
            JWTModel jwtModel = objectMapper.readValue(subject, JWTModel.class);
            return jwtModel;
        } catch (Exception e){
            return null;
        }
    }
}
