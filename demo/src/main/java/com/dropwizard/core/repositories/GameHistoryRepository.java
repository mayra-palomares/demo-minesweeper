package com.dropwizard.core.repositories;

import com.dropwizard.core.models.GameHistory;

import java.util.List;

public interface GameHistoryRepository extends BaseRepository<GameHistory> {

    List<GameHistory> getInProgressGameHistoryByUserId(String userId);
}
