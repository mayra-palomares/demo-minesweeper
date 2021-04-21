package com.dropwizard.core.mapper;

import com.dropwizard.core.models.User;
import org.bson.Document;

public class UserMapper implements BaseMapper<User>{
    @Override
    public User map(Document document) {
        User user = new User();
        user.setId(document.getObjectId("_id").toString());
        user.setCreated(document.getLong("created"));
        user.setModified(document.getLong("modified"));
        user.setDeleted(document.getBoolean("deleted"));
        user.setUsername(document.getString("username"));
        return user;
    }

    @Override
    public Document map(User user) {
        Document newDocument = new Document("created", user.getCreated())
                                .append("modified", user.getModified())
                                .append("deleted", user.isDeleted())
                                .append("username", user.getUsername());
        return newDocument;
    }
}
