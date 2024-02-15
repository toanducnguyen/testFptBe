package com.spring2.Models.Role.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String roleName;
    @Column
    private String roleId;

    public RoleEntity(String roleName, String roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }
}

