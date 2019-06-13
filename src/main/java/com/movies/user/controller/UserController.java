package com.movies.user.controller;

import com.movies.exception.ContentNotAllowedException;
import com.movies.user.domain.UserDto;
import com.movies.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            throw new ContentNotAllowedException(errors.getFieldError().getDefaultMessage());
            }
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
