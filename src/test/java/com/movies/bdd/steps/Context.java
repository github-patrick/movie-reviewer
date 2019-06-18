package com.movies.bdd.steps;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Context {

    private Response response;

}
