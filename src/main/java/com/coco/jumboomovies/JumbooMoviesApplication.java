package com.coco.jumboomovies;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.coco.jumboomovies.repositories")
public class JumbooMoviesApplication {
  public static void main(String[] args) {
    // Загружаем .env файл
    Dotenv dotenv = Dotenv.load();

    // Устанавливаем переменные окружения
    dotenv.entries().forEach(entry ->
      System.setProperty(entry.getKey(), entry.getValue()));

    SpringApplication.run(JumbooMoviesApplication.class, args);
  }
}
