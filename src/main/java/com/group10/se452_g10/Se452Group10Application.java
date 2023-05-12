package com.group10.se452_g10;

import io.micrometer.common.util.internal.logging.InternalLogger;

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

        return "Greetings from Spring Boot!--  Hey DS ";

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

}
