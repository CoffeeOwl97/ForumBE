package com.example.ForumBE.service;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;

import java.util.ArrayList;
import java.util.Optional;

public interface ForumService {
    ArrayList<Post> retrievePostGivenPostId(Long postId);

    ArrayList<Topic> retrievePostGivenTopicId(Long topicId);

    Optional<Post> findPostById(Long id);

    Optional<Topic> findTopicById(Long topicId);
}
