package com.springsecurity.demo.configuraions;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SpringSecurityAutoConfigurations {

    //Picked and Tweaked AutoConfiguration from SpringBootWebSecurityConfiguration.java
    //You can add your own custom Filters in the filter Chain from here
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) -> req
                        .antMatchers("/myAccount","/myBalance").authenticated()
                        .antMatchers("/notice").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
