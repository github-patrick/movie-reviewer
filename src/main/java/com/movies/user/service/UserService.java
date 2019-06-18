package com.movies.user.service;

import com.movies.user.domain.UserDto;
import com.movies.exception.EmailExistsException;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto) throws EmailExistsException;

    UserDto getUser(Long id) throws Exception;

    List<UserDto> getAllUsers();

    void deleteAllUsers();
}
