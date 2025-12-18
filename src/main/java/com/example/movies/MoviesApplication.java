package com.example.movies;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.movies.repositories")
public class MoviesApplication {
  public static void main(String[] args) {
    SpringApplication.run(MoviesApplication.class, args);
  }
}
