package com.coco.jumboomovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.coco.jumboomovies.repositories")
public class JumbooMoviesApplication {
    public static void main(String[] args) {
        SpringApplication.run(JumbooMoviesApplication.class, args);
    }
}
