package com.spring2.ModelsMap.UserRole.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "UserRole")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userId;
    @Column
    private String roleId;

    public UserRoleEntity(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

