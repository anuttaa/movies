package com.example.movies.integration;

import com.example.movies.entities.Actor;
import com.example.movies.repositories.ActorRepository;
import com.example.movies.repositories.AwardRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.LocalDate;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ActorControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private AwardRepository awardRepository;

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withExposedPorts(5432);

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    public void setUp() {

        RestAssured.port = port;
        Actor actor1 = new Actor();
        actor1.setName("Actor1");
        actor1.setBiography("demo");
        actor1.setDiscography(Collections.EMPTY_SET);
        actor1.setBirthday(LocalDate.now());
        //actorRepository.save(actor1);
        Actor actor2 = new Actor();
        actor2.setName("Actor2");
        actor2.setBiography("demo");
        actor2.setDiscography(Collections.EMPTY_SET);
        actor2.setBirthday(LocalDate.now());
        //actorRepository.save(actor2);
    }

    @Test
    void shouldGetAllActors() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/movies/actors")
                .then()
                .statusCode(200)
                .body(".", hasSize(20));
    }

    @Test
    void shouldGetAllAwards() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/movies/awards")
                .then()
                .statusCode(200)
                .body(".", hasSize(17));
    }
}
