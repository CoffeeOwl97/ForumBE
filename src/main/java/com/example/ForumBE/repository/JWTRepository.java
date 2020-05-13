package com.example.ForumBE.repository;

import com.example.ForumBE.model.JWTBlackListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JWTRepository extends JpaRepository<JWTBlackListEntry, Long> {
    Optional<JWTBlackListEntry> findByJwtUUID(String id);

}
