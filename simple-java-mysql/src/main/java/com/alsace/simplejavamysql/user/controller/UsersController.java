package com.alsace.simplejavamysql.user.controller;

import com.alsace.simplejavamysql.user.entity.Users;
import com.alsace.simplejavamysql.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public void add(String name, int age) {
        userService.add(name, age);
    }

    @GetMapping("get")
    public List<Users> get(Integer id){
        return userService.get(id);
    }
}
