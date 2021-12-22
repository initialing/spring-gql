package com.gql.springcloud.dao;

import com.gql.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public int signUp(@Param("user")User user);

    public User signIn(@Param("account") String account, @Param("password") String password);

    public int changePassword(@Param("account") String account, @Param("password") String password);
}
