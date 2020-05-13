package com.example.ForumBE.repository;

import com.example.ForumBE.model.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {
        Optional<ForumUser> findById(Long userId);

        Optional<ForumUser> findByUsername(String username);

}
