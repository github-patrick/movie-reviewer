package com.movies.bdd.steps;

import com.movies.bdd.utils.UserUtils;
import com.movies.exception.ApiError;
import com.movies.user.domain.AccountType;
import com.movies.user.domain.User;
import com.movies.user.domain.UserDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class UserSteps {

    private UserDto userDto;

    @Autowired
    private Context context;

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
            case "ADMIN" : userDto.setAccountType(AccountType.ADMIN);
            break;
            default: userDto.setAccountType(null);
        }
    }

    @When("I register the user")
    public void i_register_the_user() {
        context.setResponse(UserUtils.createUser(userDto));
    }

    @Then("the user should be registered")
    public void the_user_should_be_registered() {

    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMessage) {
        ApiError apiErrorResponse = context.getResponse().as(ApiError.class);
        assertThat(apiErrorResponse.getStatus()).isEqualTo(400);
        assertThat(apiErrorResponse.getMessage()).isEqualTo(errorMessage);
    }


    @Given("I have {int} registered users")
    public void i_have_registered_users(Integer numberOfUsers) {
        for (int i=0;i<numberOfUsers;i++) {
            UserUtils.createMultipleUsers();
        }
    }

    @When("I get all registered")
    public void i_get_all_registered() {
        UserUtils.getAllRegisteredUsers();
    }

    @Then("I should have {int} retrieved users")
    public void i_should_have_retrieved_users(Integer numberOfUsers) {
        List<UserDto> userDtoList = UserUtils.getAllRegisteredUsers();
        assertThat(userDtoList.size()).isEqualTo(numberOfUsers);
    }

    @When("I get the first registered user")
    public void i_get_the_first_registered_user() {
        UserDto userDto = UserUtils.getFirstRegisteredUser();
        assertThat(userDto).isNotNull();
    }

    @Then("I should have a retrieved the user")
    public void i_should_have_a_retrieved_the_user() {
        UserDto userDto = UserUtils.getFirstRegisteredUser();
        assertThat(userDto).isNotNull();
    }

    @When("I get a user that does not exist")
    public void i_get_a_user_that_does_not_exist() {
        context.setResponse(UserUtils.getUserThatDoesNotExist());
    }

    @Then("I should not have a retrieved user")
    public void i_should_not_have_a_retrieved_user() {
        ApiError apiErrorResponse = context.getResponse().as(ApiError.class);
        assertThat(apiErrorResponse.getMessage()).isEqualTo("User does not exist");

    }
}
