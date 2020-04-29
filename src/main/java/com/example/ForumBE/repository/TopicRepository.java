package com.example.ForumBE.repository;

import com.example.ForumBE.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    Optional<Topic> findById(Long topicId);
}
