package com.testwizardapp.testwizardapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableMongoRepositories
@EnableWebSecurity
public class TestwizardappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestwizardappApplication.class, args);
    }

}
