package com.example.ForumBE.security;

public class UsernameTakenException extends Exception {

    public UsernameTakenException(String username) {
        super("Username already exists for username: " + username);
    }
}
