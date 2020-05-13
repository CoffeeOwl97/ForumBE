package com.example.ForumBE.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name= "forum_topic")
@Data
@NoArgsConstructor
public class Topic {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NonNull
    @Column(name = "topic_id")
    private Long topicId;
    @NonNull
    @Column(name = "topic_name")
    private String topicName;
    @NonNull
    @Column(name = "topic_created")
    private Timestamp topicCreated;
    @NonNull
    @Column(name = "user_id")
    private Long userId;
}
