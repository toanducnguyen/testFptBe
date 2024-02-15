package com.spring2.ModelsMap.RoleTeam.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RoleTeam")
public class RoleTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String codeTeam;
    @Column
    private String roleId;

    public RoleTeamEntity(String codeTeam, String roleId) {
        this.codeTeam = codeTeam;
        this.roleId = roleId;
    }
}
