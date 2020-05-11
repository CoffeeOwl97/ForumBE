package com.example.ForumBE.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name= "forum_topic")
@Data
@NoArgsConstructor
public class Topic {
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    @NonNull
    @Column(name = "topic_id")
    private Long topicId;
    @NonNull
    @Column(name = "topic_name")
    private String topicName;
    @NonNull
    @Column(name = "topic_created")
    private String topicCreated;
    @NonNull
    @Column(name = "user_id")
    private String userId;
}
