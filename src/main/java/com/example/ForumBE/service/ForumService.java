package com.example.ForumBE.service;

import com.example.ForumBE.model.Post;

import java.util.ArrayList;
import java.util.Optional;

public interface ForumService {

    ArrayList<Post> retrievePostGivenPostId(Long postId);

    ArrayList<Post> retrievePostGivenTopicId(Long topicId);

    Optional<Post> findById(Long id);
}
