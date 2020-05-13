package com.example.ForumBE.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name= "jwt_blacklist")
@Data
@NoArgsConstructor
public class JWTBlackListEntry {
    @Id
    @NonNull
    @Column(name = "jwt_id")
    private String jwtUUID;


}
