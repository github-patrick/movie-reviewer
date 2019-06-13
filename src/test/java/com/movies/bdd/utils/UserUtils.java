package com.movies.bdd.utils;

import com.movies.user.domain.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserUtils {

    public static Response createUser(UserDto userDto) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(userDto)
            .when()
                .post("users")
            .then().log().all()
                .extract().response();
    }

    public static UserDto checkIfUserWasRegistered() {
        log.info("Checking if user was registered");

        return null;
    }
}
