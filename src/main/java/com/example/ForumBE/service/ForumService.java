package com.example.ForumBE.service;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;
import com.example.ForumBE.model.ForumUser;

import java.util.ArrayList;
import java.util.Optional;

public interface ForumService {
    ArrayList<Post> retrievePostGivenPostId(Long postId);

    ArrayList<Topic> retrieveTopicGivenTopicId(Long topicId);

    ArrayList<ForumUser> retrieveUsersGivenUserIds(ArrayList<Long> userId);

    Optional<Post> findPostById(Long id);

    Optional<ForumUser> findUserById(Long id);

    Optional<ForumUser> getUserIdFromName (String name);

    Optional<Topic> findTopicById(Long topicId);

    Optional<Topic> findExistingTopicByName(Topic topic);

    Optional<Topic> addTopic(Topic topic);

    Optional<Post> addPost(Post post);

    ArrayList<Post> retrievePostsGivenTopicId(Long topicId);

    ArrayList<Topic> retrieveAllTopics();
}
