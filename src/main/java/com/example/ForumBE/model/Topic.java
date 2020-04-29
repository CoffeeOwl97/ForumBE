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
    private Long topic_id;
    @NonNull
    private String topic_name;
    @NonNull
    private String topic_created;
    @NonNull
    private String user_id;
}
