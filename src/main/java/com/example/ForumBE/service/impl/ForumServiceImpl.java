package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;
import com.example.ForumBE.model.User;
import com.example.ForumBE.repository.PostRepository;
import com.example.ForumBE.repository.TopicRepository;
import com.example.ForumBE.repository.UserRepository;
import com.example.ForumBE.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
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
    private UserRepository userRepository;

    @Override
    public ArrayList<Post> retrievePostGivenPostId(Long postId) {
        ArrayList<Post> posts = new ArrayList<>();
        Optional<Post> post = findPostById(postId);
        posts.add(post.get());
        return posts;
    }

    @Override
    public ArrayList<Topic> retrievePostGivenTopicId(Long topicId) {
        ArrayList<Topic> topics = new ArrayList();
        Optional<Topic> topic = findTopicById(topicId);
        topics.add((Topic)topic.get());
        return topics;
    }

    @Override
    public ArrayList<User> retrieveUserGivenUserId(Long userId){
        ArrayList<User> userIds = new ArrayList<>();
        Optional<User> user = findUserById(userId);
        userIds.add(user.get());
        return userIds;
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
    public Optional<User> findUserById(Long userId){
        User user = new User();
        Optional<User> returnedUser = userRepository.findById(userId);

        if (returnedUser.isPresent()){
            user.setUser_id(returnedUser.get().getUser_id());
            user.setUser_name(returnedUser.get().getUser_name());
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
