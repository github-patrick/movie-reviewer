package com.movies.user.service;

import com.movies.user.domain.UserDto;
import com.movies.exception.EmailExistsException;

public interface UserService {

    UserDto createUser(UserDto userDto) throws EmailExistsException;

    UserDto getUser(Long id);
}
