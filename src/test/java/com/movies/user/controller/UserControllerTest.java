package com.movies.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.exception.ApiError;
import com.movies.exception.EmailExistsException;
import com.movies.exception.UserDoesNotExistException;
import com.movies.user.domain.AccountType;
import com.movies.user.domain.UserDto;
import com.movies.user.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {

    }

    @Test
    public void createUser() throws Exception {
        UserDto userDto = UserDto.builder().name("John").email("john.matt@gmail.com").password("password")
                .accountType(AccountType.USER)
                .build();
        given(userService.createUser(any(UserDto.class))).willReturn(userDto);

        MockHttpServletResponse response = mockMvc.perform(post("http://localhost/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(
                        UserDto.builder().name("patrick").email("pat@gmail.com")
                                .accountType(AccountType.USER)
                                .password("password").build()
                ))).andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        UserDto userDtoResponse = new ObjectMapper().readValue(response.getContentAsString(), UserDto.class);
        assertThat(userDtoResponse.getEmail()).isEqualTo(userDto.getEmail());
    }


    @Test
    public void invalidUserEmailCreationAttempt() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("http://localhost/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(
                        UserDto.builder().name("patrick").email("patgmail.com")
                                .password("password").build()
                ))).andDo(print())
                .andReturn().getResponse();

        ApiError apiError = new ObjectMapper().readValue(response.getContentAsString(), ApiError.class);

        assertThat(apiError.getMessage()).isNotEmpty();
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void invalidUserPasswordCreationAttempt() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("http://localhost/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(
                        UserDto.builder().name("patrick").email("pat@gmail.com")
                                .password("pass").build()
                ))).andDo(print())
                .andReturn().getResponse();

        ApiError apiError = new ObjectMapper().readValue(response.getContentAsString(), ApiError.class);

        assertThat(apiError.getMessage()).isNotEmpty();
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void invalidUserNameCreationAttempt() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("http://localhost/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(
                        UserDto.builder().name("").email("pat@gmail.com")
                                .password("password").build()
                ))).andDo(print())
                .andReturn().getResponse();

        ApiError apiError = new ObjectMapper().readValue(response.getContentAsString(), ApiError.class);

        assertThat(apiError.getMessage()).isNotEmpty();
        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void emailShouldBeUnique() throws Exception {

        given(userService.createUser(any(UserDto.class))).willThrow(new EmailExistsException("Email already exists. Use an alternative"));

        MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsBytes(
                        UserDto.builder().name("Alan").email("alan@gmail.com")
                                .accountType(AccountType.USER)
                                .password("password").build()
                ))).andDo(print())
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(400);
        ApiError apiError = new ObjectMapper().readValue(response.getContentAsString(), ApiError.class);
        assertThat(apiError.getMessage()).isEqualTo("Email already exists. Use an alternative");
    }

    @Test
    public void getAllRegisteredUsers() throws Exception {

        List<UserDto> userDtoList = Arrays.asList(UserDto.builder().email("pat@gmail.com").password("passsswwww").accountType(AccountType.USER)
            .enabled(true).build(), (UserDto.builder().email("patrick@gmail.com").password("password").accountType(AccountType.USER)
                        .enabled(true)).build());

        given(userService.getAllUsers()).willReturn(userDtoList);

        MockHttpServletResponse response = mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        List<UserDto> userDtoResponse = new ObjectMapper().readValue(response.getContentAsString(), List.class);
        assertThat(userDtoResponse.size()).isEqualTo(2);
    }

    @Test
    public void getARegisteredUser() throws Exception {
        UserDto userDto = UserDto.builder().id(1l).name("John").email("john.matt@gmail.com").password("password")
                .accountType(AccountType.USER).createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now())
                .build();

        given(userService.getUser(userDto.getId())).willReturn(userDto);


        MockHttpServletResponse response = mockMvc.perform(get("/users/"+userDto.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Ignore
    @Test(expected = UserDoesNotExistException.class)
    public void getARegisteredUserThatDoesNotExist() throws Exception {
        given(userService.getUser(1l)).willThrow(UserDoesNotExistException.class);
        MockHttpServletResponse response = mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(404);
    }

}