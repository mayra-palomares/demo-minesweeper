package com.dropwizard.resources;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.core.models.GameHistory;
import com.dropwizard.core.services.GameHistoryService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/gameHistory")
@Api(value = "/gameHistory")
@Produces(MediaType.APPLICATION_JSON)
public class GameHistoryResource {

    private GameHistoryService gameHistoryService;

    @Inject
    public GameHistoryResource(GameHistoryService gameHistoryService){
        this.gameHistoryService = gameHistoryService;
    }

    @GET
    @Path("/{userId}/inProgress")
    public List<GameHistory> getInProgressGameHistoryByUserId(@PathParam("userId") String userId) {
        return gameHistoryService.getInProgressGameHistoryByUserId(userId);
    }

    @GET
    @Path("/{userId}/game/{id}")
    public GameHistory getGameHistory(@PathParam("userId") String userId,@PathParam("id") String id) {
        return gameHistoryService.getGameHistoryById(id);
    }

    @POST
    public GameHistory addGameHistory(GameHistoryAddRequest gameHistoryRequest) {
        return gameHistoryService.addGameHistory(gameHistoryRequest);
    }

    @PUT
    @Path("/{userId}/game/{id}")
    public GameHistory updateGameHistory(@PathParam("userId") String userId,@PathParam("id") String id, GameHistory gameHistory) {
        return gameHistoryService.updateGameHistory(id, gameHistory);
    }

    @DELETE
    @Path("/{userId}/game/{id}")
    public void deleteGameHistory(@PathParam("userId") String userId, @PathParam("id") String id) {
        gameHistoryService.deleteGameHistory(id);
    }
}
