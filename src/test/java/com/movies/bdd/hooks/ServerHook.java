package com.movies.bdd.hooks;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;

@TestPropertySource("classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ServerHook {

    @Value("${com.movies.bdd.restassured.baseURI}")
    private String baseURI;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        log.info("This is running the before class");

        RestAssured.baseURI = baseURI;
        RestAssured.port = port;
    }

    @Before("@UserRegistration")
    public void deleteUsers() {
        given().when().delete("users");
    }
}
