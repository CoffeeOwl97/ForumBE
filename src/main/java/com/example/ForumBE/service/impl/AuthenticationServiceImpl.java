package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.ForumUser;
import com.example.ForumBE.model.JWTBlackListEntry;
import com.example.ForumBE.repository.ForumUserRepository;
import com.example.ForumBE.repository.JWTRepository;
import com.example.ForumBE.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    JWTRepository jwtRepository;

    @Autowired
    ForumUserRepository forumUserRepository;

    @Override
    public void blacklistJWT(String jwtId) {
        JWTBlackListEntry jwtBlackListEntry = new JWTBlackListEntry();
        jwtBlackListEntry.setJwtUUID(jwtId);
        jwtRepository.save(jwtBlackListEntry);

    }

    @Override
    public Optional<ForumUser> findExistingUsername(ForumUser user) {
        return forumUserRepository.findByUsername(user.getUsername());
    }

    @Override
    public void addUser(ForumUser user) {
        forumUserRepository.save(user);
    }
}
