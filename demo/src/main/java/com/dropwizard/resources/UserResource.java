package com.dropwizard.resources;

import com.dropwizard.core.models.User;
import com.dropwizard.core.services.UserService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
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
    public User getUser(@PathParam("userId") String id) {
        return userService.getUserById(id);
    }

    @POST
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @PUT
    @Path("/{userId}")
    public User updateUser(@PathParam("userId") String id, User user) {
        return userService.updateUser(id, user);
    }

    @DELETE
    @Path("/{userId}")
    public void deleteUser(@PathParam("userId") String id) {
        userService.deleteUser(id);
    }
}
