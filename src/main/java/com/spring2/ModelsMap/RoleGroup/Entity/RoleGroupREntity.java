package com.spring2.ModelsMap.RoleGroup.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RoleGroupR")
public class RoleGroupREntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String roleId;

    @Column
    private String groupId;

    public RoleGroupREntity(String roleId, String groupId) {
        this.roleId = roleId;
        this.groupId = groupId;
    }
}
