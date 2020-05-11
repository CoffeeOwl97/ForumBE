package com.example.ForumBE.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table (name = "forum_users")
@NoArgsConstructor
@Data
public class User {
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    @NonNull
    public Long user_id;
    @NonNull
    private String user_name;
    @NonNull
    private String hash;
    @NonNull
    private String salt;

}