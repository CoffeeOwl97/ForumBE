package com.example.ForumBE.service;

import com.example.ForumBE.model.ForumUser;

import java.util.Optional;

public interface AuthenticationService {

    void blacklistJWT(String jwtID);

    Optional<ForumUser> findExistingUsername(ForumUser user);

    void addUser(ForumUser user);
}
