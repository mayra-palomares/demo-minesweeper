package com.dropwizard.core.services.impl;

import com.dropwizard.api.requests.UserAddRequest;
import com.dropwizard.core.models.User;
import com.dropwizard.core.repositories.UserRepository;
import com.dropwizard.core.services.UserService;
import com.google.inject.Inject;

import java.util.List;

public class UserServiceImpl implements UserService {
    final static String INVALID_USERNAME = "Invalid Username";
    final static String USERNAME_EXISTS = "The username already exists. Please use a different username";

    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.list();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User addUser(UserAddRequest userRequest) throws Exception {
        validateUserRequest(userRequest);

        String username = userRequest.getUsername();
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            throw new Exception(USERNAME_EXISTS);
        }

        //Create a new user
        user = new User();
        user.prepare();
        user.setUsername(userRequest.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        return userRepository.update(id, user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.removeById(id);
    }

    @Override
    public User enterUser(UserAddRequest userRequest) throws Exception {
        validateUserRequest(userRequest);
        User user = userRepository.findUserByUsername(userRequest.getUsername());
        if(user == null){
            user = addUser(userRequest);
        }
        return user;
    }

    private void validateUserRequest(UserAddRequest userRequest) throws Exception {
        if(userRequest.getUsername() == null){
            throw new Exception(INVALID_USERNAME);
        }
    }
}
