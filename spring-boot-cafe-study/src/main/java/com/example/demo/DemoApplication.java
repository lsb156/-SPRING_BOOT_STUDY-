package com.example.demo;

import com.example.demo.jpa.QUserEntity;
import com.example.demo.jpa.UserEntity;
import com.example.demo.jpa.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(DemoApplication.class, args);

//        UserRepository userRepository = context.getBean(UserRepository.class);
//        UserEntity userEntity = userRepository.findByUsername("test");
//        System.out.println("이른 : " + userEntity.getUsername());

    }

}
