package com.movies.user.service;

import com.movies.exception.UserDoesNotExistException;
import com.movies.user.domain.User;
import com.movies.user.domain.UserDto;
import com.movies.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void createUser() throws Exception {

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

    @Test
    public void getExistingUser() throws Exception {
        User user = User.builder().id(1L)
                .email("jerry@google.com")
                .password("hellotest")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .enabled(true).build();

        when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(1l);

        assertThat(userDto.getEmail(), is("jerry@google.com"));
    }


    @Ignore
    @Test(expected = UserDoesNotExistException.class)
    public void getNonExistentUser() throws Exception {
        User user = User.builder().id(1L)
                .email("jerry@google.com")
                .password("hellotest")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .enabled(true).build();

        when(userRepository.findById(3l)).thenReturn(Optional.of(user));
        userService.getUser(3l);

    }

}