package com.movies.user.service;

import com.movies.exception.UserDoesNotExistException;
import com.movies.user.domain.User;
import com.movies.user.domain.UserDto;
import com.movies.exception.EmailExistsException;
import com.movies.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) throws EmailExistsException  {

        checkIfEmailExists(userDto.getEmail());

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUser(Long id) throws Exception {

        if (!userRepository.findById(id).isPresent()) {
            throw new UserDoesNotExistException("User does not exist");
        } else {
            return modelMapper.map(userRepository.findById(id).get(), UserDto.class);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userList = new ArrayList<>();
        userRepository.findAll().forEach(
                user -> userList.add(modelMapper.map(user,UserDto.class)));
        return userList;
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    private void checkIfEmailExists(String email) throws EmailExistsException {
        if (!userRepository.findByEmail(email).isPresent()) {
            return;
        } else {
            throw new EmailExistsException("Email already exists. Use an alternative");
        }
    }
}
