package com.example.ForumBE.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ForumBE.model.*;
import com.example.ForumBE.service.ForumService;
import com.example.ForumBE.service.exception.InvalidTopicException;
import com.example.ForumBE.service.exception.TopicTakenException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.ForumBE.security.SecurityConstants.*;

@RestController
@AllArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @GetMapping(path = "/retrieve-post/{postId}")
    @ApiOperation(value = "Handles retrieving posts for a given post identifier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was successful and post was retrieved",
                    response = PostResponse.class),
            @ApiResponse(code = 404, message = "No resources found for the given post id")})
    public ResponseEntity<PostResponse> retrievePost(
            @PathVariable Long postId) {
        return ResponseEntity.ok(
                PostResponse.builder()
                        .posts(forumService.retrievePostGivenPostId(postId))
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping(
            path = {"/retrieve-topic/{topicId}"}
    )
    @ApiOperation("Handles retrieving posts for a given topic identifier")
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "Request was successful and posts were retrieved",
            response = TopicResponse.class
    ), @ApiResponse(
            code = 404,
            message = "No resources found for the given topic id"
    )})
    public ResponseEntity<TopicResponse> retrieveTopicForTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(
                TopicResponse.builder()
                        .topic(this.forumService.retrieveTopicGivenTopicId(topicId))
                        .status(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping(path = "/retrieve-user/{userId}")
    @ApiOperation(value = "Handles retrieving users for a given user identifier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was successful and user was retrieved",
                    response = PostResponse.class),
            @ApiResponse(code = 404, message = "No resources found for the given user id")})
    public ResponseEntity<ForumUserResponse> retrieveUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ForumUserResponse.builder()
                        .users(forumService.retrieveUserGivenUserId(userId))
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save-topic")
    @ApiOperation(value = "Handles saving topic")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Request was successful and topic was saved",
                    response = PostResponse.class),
            @ApiResponse(code = 400, message = "Topic failed to be saved")})
    public ResponseEntity<TopicResponse> saveTopic(@RequestBody Topic topic, HttpServletRequest request) throws TopicTakenException {
        Optional<Topic> existingTopic = forumService.findExistingTopicByName(topic);
        if(existingTopic.isPresent()){
            throw new TopicTakenException(topic.getTopicName());
        }
        Timestamp timestamp = Timestamp.from(Instant.now());
        topic.setTopicCreated(timestamp);
        String jwtToken = request.getHeader(HEADER_STRING);
        String subject = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(jwtToken.replace(TOKEN_PREFIX, ""))
                .getSubject();

        Optional<ForumUser> returnUser = forumService.getUserIdFromName(subject);
        if (returnUser.isPresent()){
            topic.setUserId(returnUser.get().getUserId());
            Optional<Topic> returnedTopic = this.forumService.addTopic(topic);
            ArrayList<Topic> topicResponse = new ArrayList<>();

            if (returnedTopic.isPresent()) {
                topicResponse.add(returnedTopic.get());
                return ResponseEntity.ok(
                        TopicResponse.builder()
                                .topic(topicResponse)
                                .status(HttpStatus.CREATED.value())
                                .build()
                );
            }

        }
     return ResponseEntity.badRequest().build();
    }

    @PostMapping("/save-post")
    @ApiOperation(value = "Handles saving posts")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Request was successful and post was saved",
                    response = PostResponse.class),
            @ApiResponse(code = 400, message = "Post failed to be saved")})
    public ResponseEntity<PostResponse> savePost(@RequestBody Post post, HttpServletRequest request) throws InvalidTopicException {
        Optional<Topic> validTopic = forumService.findTopicById(post.getTopicId());
        if(!validTopic.isPresent()){
            throw new InvalidTopicException(post.getTopicId().toString());
        }

        Timestamp timestamp = Timestamp.from(Instant.now());
        post.setPostCreated(timestamp);
        String jwtToken = request.getHeader(HEADER_STRING);
        String subject = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(jwtToken.replace(TOKEN_PREFIX, ""))
                .getSubject();

        Optional<ForumUser> returnUser = forumService.getUserIdFromName(subject);
        if (returnUser.isPresent()){
            post.setUserId(returnUser.get().getUserId());
            Optional<Post> returnedPost = this.forumService.addPost(post);
            ArrayList<Post> postResponse = new ArrayList<>();

            if (returnedPost.isPresent()) {
                postResponse.add(returnedPost.get());
                return ResponseEntity.ok(
                        PostResponse.builder()
                                .posts(postResponse)
                                .status(HttpStatus.CREATED.value())
                                .build()
                );
            }


        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(
            path = {"/retrieve-posts/{topicId}"}
    )
    @ApiOperation("Handles retrieving all posts for a given topic identifier")
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "Request was successful and posts were retrieved",
            response = TopicResponse.class
    ), @ApiResponse(
            code = 404,
            message = "No resources found for the given topic id"
    )})
    public ResponseEntity<PostResponse> retrievePostsForTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(
                PostResponse.builder()
                        .posts(this.forumService.retrievePostsGivenTopicId(topicId))
                        .status(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping(
            path = {"/retrieve-topics"}
    )
    @ApiOperation("Handles retrieving all topics")
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "Request was successful and topics were retrieved",
            response = TopicResponse.class
    ), @ApiResponse(
            code = 404,
            message = "No resources found"
    )})
    public ResponseEntity<TopicResponse> retrieveAllTopics() {
        return ResponseEntity.ok(
                TopicResponse.builder()
                        .topic(this.forumService.retrieveAllTopics())
                        .status(HttpStatus.OK.value())
                        .build());
    }

}
