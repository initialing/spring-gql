package com.gql.springcloud.dao;

import com.gql.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public int signUp(@Param("user")User user);
}
