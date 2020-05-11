package com.example.ForumBE.controller;

import com.example.ForumBE.model.PostResponse;
import com.example.ForumBE.model.TopicResponse;
import com.example.ForumBE.model.UserResponse;
import com.example.ForumBE.service.ForumService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;

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
    public ResponseEntity<TopicResponse> retrievePostsForTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(
                TopicResponse.builder()
                        .topic(this.forumService.retrievePostGivenTopicId(topicId))
                        .status(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping(path = "/retrieve-user/{userId}")
    @ApiOperation(value = "Handles retrieving users for a given user identifier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was successful and user was retrieved",
                    response = PostResponse.class),
            @ApiResponse(code = 404, message = "No resources found for the given user id")})
    public ResponseEntity<UserResponse> retrieveUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                UserResponse.builder()
                        .users(forumService.retrieveUserGivenUserId(userId))
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

}
