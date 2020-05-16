package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;
import com.example.ForumBE.model.ForumUser;
import com.example.ForumBE.repository.PostRepository;
import com.example.ForumBE.repository.TopicRepository;
import com.example.ForumBE.repository.ForumUserRepository;
import com.example.ForumBE.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ForumUserRepository forumUserRepository;

    @Override
    public ArrayList<Post> retrievePostGivenPostId(Long postId) {
        ArrayList<Post> posts = new ArrayList<>();
        Optional<Post> post = findPostById(postId);
        posts.add(post.get());
        return posts;
    }

    @Override
    public ArrayList<Topic> retrieveTopicGivenTopicId(Long topicId) {
        ArrayList<Topic> topics = new ArrayList<>();
        Optional<Topic> topic = findTopicById(topicId);
        topics.add((Topic)topic.get());
        return topics;
    }

    @Override
    public ArrayList<ForumUser> retrieveUsersGivenUserIds(ArrayList<Long> userIds){
        ArrayList<ForumUser> users = new ArrayList<>();
        for(Long id : userIds){
            Optional<ForumUser> user = findUserById(id);
            users.add(user.get());
        }
        return users;
    }

    @Override
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Optional<Topic> findTopicById(Long topicId) {
        return topicRepository.findById(topicId);
    }

    @Override
    public Optional<ForumUser> findUserById(Long userId){
        ForumUser user = new ForumUser();
        Optional<ForumUser> returnedUser = forumUserRepository.findById(userId);

        if (returnedUser.isPresent()){
            user.setUserId(returnedUser.get().getUserId());
            user.setUsername(returnedUser.get().getUsername());
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Topic> addTopic(Topic topic){
       return Optional.of(topicRepository.save(topic));
    }

    @Override
    public Optional<Topic> findExistingTopicByName(Topic topic){
        return topicRepository.findByTopicName(topic.getTopicName());
    }

    @Override
    public ArrayList<Post> retrievePostsGivenTopicId(Long topicId){
        return postRepository.findPostByTopicId(topicId);
    }

    @Override
    public ArrayList<Topic> retrieveAllTopics(){
        return (ArrayList<Topic>) topicRepository.findAll();
    }

    @Override
    public Optional<ForumUser> getUserIdFromName (String name){
        return forumUserRepository.findByUsername(name);
    }

    public Optional<Post> addPost (Post post){
        return Optional.of(postRepository.save(post));
    }
}
