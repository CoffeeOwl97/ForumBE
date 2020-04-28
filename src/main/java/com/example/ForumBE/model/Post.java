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
    private Long post_id;
    @NonNull
    private String post_text;
    @NonNull
    private String user_id;
    @NonNull
    private String topic_id;
    @NonNull
    private String post_created;


}
