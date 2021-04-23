package com.dropwizard.core.services;

import com.dropwizard.api.requests.UserAddRequest;
import com.dropwizard.core.models.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(String id);

    User addUser(UserAddRequest user) throws Exception;

    User updateUser(String id, User user);

    void deleteUser(String id);

    User enterUser(UserAddRequest user) throws Exception;
}
