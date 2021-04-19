package com.dropwizard.core.models;

public class User extends BaseModel {
    public static final String COLLECTION_USERS = "users";

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getCollectionName() {
        return COLLECTION_USERS;
    }
}
