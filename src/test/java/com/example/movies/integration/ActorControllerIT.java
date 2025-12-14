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
import java.time.LocalDate;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private AwardRepository awardRepository;

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> {
            String v = System.getenv("MOVIES_SPRING_DATASOURCE_URL");
            return v != null && !v.isBlank() ? v : "jdbc:postgresql://localhost:5432/movies";
        });
        registry.add("spring.datasource.username", () -> {
            String v = System.getenv("MOVIES_POSTGRES_USER");
            return v != null && !v.isBlank() ? v : "postgres";
        });
        registry.add("spring.datasource.password", () -> {
            String v = System.getenv("MOVIES_POSTGRES_PASSWORD");
            return v != null && !v.isBlank() ? v : "postgres";
        });
        registry.add("spring.mail.password", () -> {
            String v = System.getenv("MAIL_PASSWORD");
            return v != null && !v.isBlank() ? v : "dummy-password";
        });
        registry.add("application.security.jwt.secret-key", () -> {
            String v = System.getenv("JWT_SECRET");
            return v != null && !v.isBlank() ? v : "dGVzdHNlY3JldGtleWJhc2U2NA==";
        });
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
                .body(".", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    void shouldGetAllAwards() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/movies/awards")
                .then()
                .statusCode(200)
                .body(".", hasSize(greaterThanOrEqualTo(1)));
    }
}
