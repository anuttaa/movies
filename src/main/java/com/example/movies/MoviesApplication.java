package com.example.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.movies.repositories")
public class MoviesApplication {
  public static void main(String[] args) {
    SpringApplication.run(MoviesApplication.class, args);
  }
}
