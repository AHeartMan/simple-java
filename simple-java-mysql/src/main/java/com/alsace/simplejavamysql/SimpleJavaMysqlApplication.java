package com.alsace.simplejavamysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.alsace.simplejavamysql.user.mapper")
@SpringBootApplication
public class SimpleJavaMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJavaMysqlApplication.class, args);
    }

}
