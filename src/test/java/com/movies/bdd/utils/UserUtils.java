package com.movies.bdd.utils;

import com.movies.exception.ApiError;
import com.movies.user.domain.AccountType;
import com.movies.user.domain.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

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

    public static void createMultipleUsers() {
        given()
                .contentType(ContentType.JSON)
                .body(createUserDto())
                .log().all()
        .when()
                .post("users")
        .then().statusCode(201);
    }

    private static UserDto createUserDto() {
        DataFactory dataFactory = new DataFactory();
        UserDto userDto = UserDto.builder()
                .name(dataFactory.getName())
                .email(dataFactory.getEmailAddress())
                .password(dataFactory.getRandomText(10))
                .accountType(AccountType.USER)
                .enabled(true).build();
        return userDto;


    }

    public static List<UserDto> getAllRegisteredUsers() {
        UserDto[] userListDto = given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .when()
                .get("users")
        .then().statusCode(200).extract().as(UserDto[].class);

        return Arrays.asList(userListDto);
    }

    public static UserDto getFirstRegisteredUser() {

        Long id = getAllRegisteredUsers().stream().
                filter(userDto -> userDto.getId() > 0).findFirst().get().getId();

        UserDto userDto = given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .pathParam("userId", id).log().all()
        .when()
                .get("users/{userId}")
        .then().log().all()
                .statusCode(200)
                .extract().as(UserDto.class);
        return userDto;
    }

    public static Response getUserThatDoesNotExist() {
         return given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .pathParam("userId", "999999999").log().all()
                .when()
                .get("users/{userId}")
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }
}
