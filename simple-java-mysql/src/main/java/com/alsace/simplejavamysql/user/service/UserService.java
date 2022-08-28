package com.alsace.simplejavamysql.user.service;

import com.alsace.simplejavamysql.user.entity.Users;
import com.alsace.simplejavamysql.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void add(String name, int age) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        userMapper.addUser(user);
    }

    public List<Users> get(Integer id){
        return userMapper.getUser(id);
    }
}
