package com.example.ForumBE.service.impl;

import com.example.ForumBE.model.Post;
import com.example.ForumBE.repository.PostRepository;
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

    @Override
    public ArrayList<Post> retrievePostGivenPostId(Long postId) {
        ArrayList<Post> posts = new ArrayList<>();
        Optional<Post> post = findById(postId);
        posts.add(post.get());
        return posts;
    }

    @Override
    public ArrayList<Post> retrievePostGivenTopicId(Long topicId) {
        ArrayList<Post> posts = new ArrayList<>();
        Optional<Post> post = findById(topicId);
        posts.add(post.get());
        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {

        return postRepository.findById(id);
    }
}
