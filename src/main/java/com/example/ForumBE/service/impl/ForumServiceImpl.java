package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.model.Topic;
import com.example.ForumBE.repository.PostRepository;
import com.example.ForumBE.repository.TopicRepository;
import com.example.ForumBE.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;

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
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Optional<Topic> findTopicById(Long topicId) {
        return topicRepository.findById(topicId);
    }
}
