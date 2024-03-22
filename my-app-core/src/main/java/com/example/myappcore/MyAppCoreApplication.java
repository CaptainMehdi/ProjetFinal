package com.example.myappcore;

import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.model.User;
import com.example.myappcore.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MyAppCoreApplication implements CommandLineRunner {

    private final LoginService loginService;
    public static void main(String[] args) {
        SpringApplication.run(MyAppCoreApplication.class, args);
    }

    public void run(String... args) throws Exception {
        loginService.register(new UserRegistrationDto("mehdi@gmail.com","mehdi","Mehdi","Diyani"));
        System.out.println("DONE");
    }

}
