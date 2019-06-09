package com.movies.bdd.steps;

import com.movies.bdd.utils.UserUtils;
import com.movies.user.domain.AccountType;
import com.movies.user.domain.UserDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


public class UserSteps {

    private UserDto userDto;

    @Given("I have a user")
    public void i_have_a_user() {
        userDto = new UserDto();
    }

    @Given("the user has a name of {string}")
    public void the_user_has_a_name_of(String name) {
        userDto.setName(name);
    }

    @Given("the user has an email of {string}")
    public void the_user_has_an_email_of(String email) {
       userDto.setEmail(email);
    }

    @Given("the user has a password of {string}")
    public void the_user_has_a_password_of(String password) {
        userDto.setPassword(password);
    }

    @Given("the user has an enabled flag of {string}")
    public void the_user_has_an_enabled_flag_of(String enabled) {
        userDto.setEnabled(Boolean.valueOf(enabled));
    }

    @Given("the user has an account type of {string}")
    public void the_user_has_an_account_type_of(String accountType) {

        switch (accountType) {
            case "USER" : userDto.setAccountType(AccountType.USER);
            break;
            default: userDto.setAccountType(AccountType.ADMIN);
        }
    }

    @When("I register the user")
    public void i_register_the_user() {
        given().log().all()
                .contentType(ContentType.JSON)
                .body(userDto)
        .when()
                .post("users")
        .then().log().all().statusCode(201);

    }

    @Then("I the user is registered")
    public void i_the_user_is_registered() {

    }
}
