package com.example.ForumBE.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name= "forum_post")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "post_id")
    private Long postId;
    @NonNull
    @Column(name = "post_text")
    private String postText;
    @NonNull
    @Column(name = "user_id")
    private Long userId;
    @NonNull
    @Column(name = "topic_id")
    private Long topicId;
    @NonNull
    @Column(name = "post_created")
    private Timestamp postCreated;


}
