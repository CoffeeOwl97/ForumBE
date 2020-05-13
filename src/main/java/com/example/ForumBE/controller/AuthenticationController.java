package com.example.ForumBE.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ForumBE.model.ForumUser;
import com.example.ForumBE.model.JWTBlackListEntry;
import com.example.ForumBE.security.UsernameTakenException;
import com.example.ForumBE.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.example.ForumBE.security.SecurityConstants.*;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {

    private AuthenticationService authenticationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(AuthenticationService authenticationService,
                                    BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationService = authenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ForumUser user) throws UsernameTakenException {
        Optional<ForumUser> existingUser = authenticationService.findExistingUsername(user);
        if(existingUser.isPresent()){
            throw new UsernameTakenException(user.getUsername());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        authenticationService.addUser(user);
    }

    @PostMapping("/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws UsernameTakenException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            String jwtToken = request.getHeader(HEADER_STRING);

            String tokenId = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(jwtToken.replace(TOKEN_PREFIX, ""))
                    .getId();

            authenticationService.blacklistJWT(tokenId);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);


    }
}
