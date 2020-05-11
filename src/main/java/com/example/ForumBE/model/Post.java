package com.example.ForumBE.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name= "forum_post")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @NonNull
    @Column(name = "post_id")
    private Long postId;
    @NonNull
    @Column(name = "post_text")
    private String postText;
    @NonNull
    @Column(name = "user_id")
    private String userId;
    @NonNull
    @Column(name = "topic_id")
    private String topicId;
    @NonNull
    @Column(name = "post_created")
    private String postCreated;


}
