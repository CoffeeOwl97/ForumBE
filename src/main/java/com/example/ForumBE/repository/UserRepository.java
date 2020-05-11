package com.example.ForumBE.repository;

import com.example.ForumBE.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
        Optional<User> findById(Long topicId);
}
