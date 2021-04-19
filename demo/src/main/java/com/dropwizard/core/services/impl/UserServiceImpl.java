package com.dropwizard.core.services.impl;

import com.dropwizard.core.models.User;
import com.dropwizard.core.repositories.UserRepository;
import com.dropwizard.core.services.UserService;
import com.google.inject.Inject;

import java.util.List;

public class UserServiceImpl implements UserService {

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
    public User addUser(User user) {
        user.prepare();
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
}
