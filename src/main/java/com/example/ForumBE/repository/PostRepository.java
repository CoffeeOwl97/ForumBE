package com.example.ForumBE.repository;

import com.example.ForumBE.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    ArrayList<Post> findPostByTopicId(Long topicId);
}
