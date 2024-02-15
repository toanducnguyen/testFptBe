package com.spring2.Models.User.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private String userId;
    @Column
    private boolean isAdmin;

    public UserEntity(String name, String userId, boolean isAdmin) {
        this.name = name;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

}
