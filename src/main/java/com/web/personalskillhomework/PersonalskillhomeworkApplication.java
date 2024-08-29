package com.web.personalskillhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalskillhomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalskillhomeworkApplication.class, args);
    }

}
