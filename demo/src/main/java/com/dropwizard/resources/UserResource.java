package com.dropwizard.resources;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Api(value = "/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public String getUser() {
        return "Testing API";
    }
}
