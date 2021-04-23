package com.dropwizard.resources;

import com.dropwizard.api.requests.GameHistoryAddRequest;
import com.dropwizard.api.requests.GameMoveRequest;
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
    public GameHistory getGameHistoryById(@PathParam("userId") String userId,@PathParam("id") String gameHistoryId) {
        return gameHistoryService.getGameHistoryById(gameHistoryId);
    }

    @POST
    @Path("/{userId}/game")
    public GameHistory addGameHistory(@PathParam("userId") String userId, GameHistoryAddRequest gameHistoryRequest) throws Exception {
        return gameHistoryService.addGameHistory(userId, gameHistoryRequest);
    }

    @PUT
    @Path("/{userId}/game/{id}")
    public GameHistory updateGameHistory(@PathParam("userId") String userId,@PathParam("id") String gameHistoryId, GameHistory gameHistory) {
        return gameHistoryService.updateGameHistory(userId, gameHistoryId, gameHistory);
    }

    @DELETE
    @Path("/{userId}/game/{id}")
    public void deleteGameHistory(@PathParam("userId") String userId, @PathParam("id") String gameHistoryId) {
        gameHistoryService.deleteGameHistory(userId, gameHistoryId);
    }

    @POST
    @Path("/{userId}/game/{id}/move")
    public GameHistory makeMove(@PathParam("userId") String userId, @PathParam("id") String gameHistoryId, GameMoveRequest request) {
        return gameHistoryService.makeGameMove(gameHistoryId, request);
    }
}
