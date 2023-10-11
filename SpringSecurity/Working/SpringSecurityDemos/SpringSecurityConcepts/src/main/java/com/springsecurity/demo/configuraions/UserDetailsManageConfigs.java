package com.springsecurity.demo.configuraions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class UserDetailsManageConfigs {

    //1.InMemoryUserDetailManager
//    @Bean
//    public InMemoryUserDetailsManager getCustomInMemoryUserDetailManager(){
//        UserDetails user1 = User.withUsername("TestInMemoryUser1")
//                .password("TestInMemoryUser1").authorities("admin").build();
//        UserDetails user2 = User.withUsername("TestInMemoryUser2")
//                .password("TestInMemoryUser2").authorities("admin").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
//
    @Bean
    public PasswordEncoder getDefaultPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }

    //2.JdbcUserDetailManager
    //DB Schema of Default JdbcUserDetailManager is: DEFAULT_USER_SCHEMA_DDL_LOCATION = "org/springframework/security/core/userdetails/jdbc/users.ddl";
    /* So Create Tables as per this ...
    *
    *       CREATE TABLE users (
    username VARCHAR2(50 CHAR) NOT NULL,
    password VARCHAR2(500 CHAR) NOT NULL,
    enabled NUMBER(1, 0) NOT NULL CHECK (enabled IN (0, 1)),
    CONSTRAINT pk_users PRIMARY KEY (username)
);


CREATE TABLE authorities (
    username VARCHAR2(50 CHAR) NOT NULL,
    authority VARCHAR2(50 CHAR) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO users (username, password, enabled)
VALUES ('dummyuser', 'dummypassword', 1);

INSERT INTO authorities (username, authority)
VALUES ('dummyuser', 'ROLE_USER');



COMMIT;

    *
    * */

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService getJdbcUserDetailManager(){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
