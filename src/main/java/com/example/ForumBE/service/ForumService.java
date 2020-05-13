package com.example.ForumBE.service;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;
import com.example.ForumBE.model.ForumUser;


import java.util.ArrayList;
import java.util.Optional;

public interface ForumService {
    ArrayList<Post> retrievePostGivenPostId(Long postId);

    ArrayList<Topic> retrievePostGivenTopicId(Long topicId);

    ArrayList<ForumUser> retrieveUserGivenUserId(Long userId);

    Optional<Post> findPostById(Long id);

    Optional<ForumUser> findUserById(Long id);

    Optional<Topic> findTopicById(Long topicId);
}
