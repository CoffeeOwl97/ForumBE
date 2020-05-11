package com.example.ForumBE.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table (name = "forum_users")
@NoArgsConstructor
@Data
public class ForumUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NonNull
    @Column(name = "user_id")
    private Long userId;
    @NonNull
    @Column(name = "user_name")
    private String username;
    @NonNull
    @Column(name = "user_password")
    private String password;

}