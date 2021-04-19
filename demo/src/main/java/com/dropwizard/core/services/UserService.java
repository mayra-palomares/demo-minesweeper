package com.dropwizard.core.services;

import com.dropwizard.core.models.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(String id);

    User addUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);
}
