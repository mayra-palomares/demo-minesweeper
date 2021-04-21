package com.dropwizard.core.mapper;

import org.bson.Document;

public interface BaseMapper<T> {

    T map(final Document document);

    Document map(final T model);
}
