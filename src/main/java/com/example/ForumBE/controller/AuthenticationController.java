package com.example.ForumBE.controller;

import com.example.ForumBE.model.ForumUser;
import com.example.ForumBE.repository.ForumUserRepository;
import com.example.ForumBE.security.UsernameTakenException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

    private ForumUserRepository forumUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(ForumUserRepository forumUserRepository,
                                    BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ForumUser user) throws UsernameTakenException {
        Optional<ForumUser> existingUser = forumUserRepository.findByUsername(user.getUsername());
        if(existingUser.isPresent()){
            throw new UsernameTakenException(user.getUsername());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        forumUserRepository.save(user);
    }
}
