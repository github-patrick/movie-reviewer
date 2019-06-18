package com.movies.user.controller;

import com.movies.exception.ApiError;
import com.movies.exception.ContentNotAllowedException;
import com.movies.user.domain.UserDto;
import com.movies.user.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws Exception {
        if(!(userId instanceof Long)) {
            throw new Exception();
        }

        return (new ResponseEntity(userService.getUser(userId), HttpStatus.OK));
    }

    @DeleteMapping
    public void deleteAll() {
        userService.deleteAllUsers();
    }
}
