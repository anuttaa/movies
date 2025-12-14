package com.example.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.movies.repositories")
public class JumbooMoviesApplication {
  public static void main(String[] args) {
    SpringApplication.run(JumbooMoviesApplication.class, args);
  }
}
