package com.movies.user.service;

import com.movies.user.domain.User;
import com.movies.user.domain.UserDto;
import com.movies.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper);
    }

    @Test
    public void createUser() {

        UserDto userDto = UserDto.builder().id(1L)
                .email("jerry@google.com")
                .password("hellotest")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .enabled(true).build();

        User user = User.builder().id(1L)
                .email("jerry@google.com")
                .password("hellotest")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .enabled(true).build();

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        UserDto userDtoCreated = userService.createUser(userDto);
        assertThat(userDtoCreated.getEmail(), is(userDto.getEmail()));

    }
}