package com.springsecurity.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.springsecurity.demo")
@EnableWebSecurity //Optional as Sp.Boot is Smart enough to look from dependencies that we are using Spring-Security and thus smartly Autoconfigures it.But is required when u have simple Spring app.
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }


}