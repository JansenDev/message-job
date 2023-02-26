package com.mrjoi.messagejob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessageJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageJobApplication.class, args);
    }

}
