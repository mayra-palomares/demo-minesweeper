package com.dropwizard.core.repositories;

import com.dropwizard.core.models.BaseModel;

import java.util.List;

public interface BaseRepository<T extends BaseModel> {

    List<T> list();

    T getById(String entityId);

    T save(T model);

    T update(String entityId, T model);

    void removeById(String entityId);
}
