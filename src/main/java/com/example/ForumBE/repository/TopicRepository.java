package com.example.ForumBE.repository;

import com.example.ForumBE.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findById(Long topicId);

    Optional<Topic> findByTopicName(String topicName);
}
