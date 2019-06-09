package com.movies.user.service;

import com.movies.user.domain.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(Long id);
}
