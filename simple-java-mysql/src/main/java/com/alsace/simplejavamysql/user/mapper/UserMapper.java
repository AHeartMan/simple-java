package com.alsace.simplejavamysql.user.mapper;

import com.alsace.simplejavamysql.user.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer addUser(Users user);

    List<Users> getUser(@Param("id") Integer id);
}
