package com.alsace.simplejavastorm;

import com.alsace.simplejavastorm.storm.Topology;
import com.alsace.simplejavastorm.util.InitSpringBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SimpleJavaStormApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SimpleJavaStormApplication.class, args);
        InitSpringBean springBean = new InitSpringBean();
        springBean.setApplicationContext(context);
        Topology app = context.getBean(Topology.class);
        app.runStorm(args);
    }

}
