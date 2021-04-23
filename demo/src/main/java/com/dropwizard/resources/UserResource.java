package com.dropwizard.resources;

import com.dropwizard.api.requests.UserAddRequest;
import com.dropwizard.core.models.User;
import com.dropwizard.core.services.UserService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Api(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GET
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("/{userId}")
    public User getUser(@PathParam("userId") String userId) {
        return userService.getUserById(userId);
    }

    @POST
    public User addUser(@Valid UserAddRequest user) throws Exception {
        return userService.addUser(user);
    }

    @PUT
    @Path("/{userId}")
    public User updateUser(@PathParam("userId") String userId, User user) {
        return userService.updateUser(userId, user);
    }

    @DELETE
    @Path("/{userId}")
    public void deleteUser(@PathParam("userId") String userId) {
        userService.deleteUser(userId);
    }

    @POST
    @Path("/enterUser")
    public User enterUser(@Valid UserAddRequest user) throws Exception {
        return userService.enterUser(user);
    }
}
