package com.group10.se452_g10;

import com.group10.se452_g10.account.UserRoleType;
import com.group10.se452_g10.security.RegistrationService;
import com.group10.se452_g10.security.SignupRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Log4j2
@SpringBootApplication
@RestController
@EnableCaching
public class Se452Group10Application {
    @Value("${environment}")
    private String env;

    public static void main(String[] args) {
        SpringApplication.run(Se452Group10Application.class, args

        );
    }

    @GetMapping("/")
    public String index() {

        return "Greetings, from Spring Boot!11--  Hey DS ";

    }

    @Bean
    public CommandLineRunner showLogLevel() {
        return (args) -> {
            System.out.println(env);
            log.debug("Debug");
            log.info("Info");
            log.warn("Warning");
            log.error("Error");
        };
    }

    @Bean
    public CommandLineRunner addUser(RegistrationService register) {
        return (args) -> {
            var request = new SignupRequest();
            request.setUsername("admin");
            request.setPassword("admin");
            request.setEmail("admin@depaul.edu");
            request.setRole(UserRoleType.ADMIN);
            register.registerUser(request);

            var request1 = new SignupRequest();
            request1.setUsername("tea");
            request1.setPassword("admin");
            request1.setEmail("admin@depaul.edu");
            request1.setRole(UserRoleType.TEACHER);
            register.registerUser(request1);

            var request3 = new SignupRequest();
            request3.setUsername("student");
            request3.setPassword("student");
            request3.setEmail("admin@depaul.edu");
            request3.setRole(UserRoleType.STUDENT);
            register.registerUser(request3);
        };
    }
}
