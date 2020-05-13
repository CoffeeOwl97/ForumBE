package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.ForumUser;
import com.example.ForumBE.repository.ForumUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ForumUserRepository forumUserRepository;


    public UserDetailsServiceImpl(ForumUserRepository forumUserRepository) {
        this.forumUserRepository = forumUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ForumUser> applicationUser = forumUserRepository.findByUsername(username);
        if(!applicationUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        return new User(applicationUser.get().getUsername(),applicationUser.get().getPassword(), Collections.emptyList());
    }
}
