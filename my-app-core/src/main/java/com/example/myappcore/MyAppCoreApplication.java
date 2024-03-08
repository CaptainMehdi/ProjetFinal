package com.example.myappcore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAppCoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyAppCoreApplication.class, args);
    }

    public void run(String... args) throws Exception {
        System.out.println("DONE");
    }

}
