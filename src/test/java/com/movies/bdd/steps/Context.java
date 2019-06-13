package com.movies.bdd.steps;

import com.movies.user.domain.UserDto;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Context {

    private Response response;

}
