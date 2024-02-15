package com.spring2.ModelsMap.TeamGroup.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TeamGroup")
public class TeamGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String codeTeam;
    @Column
    private String groupId;

    public TeamGroupEntity(String codeTeam, String groupId) {
        this.codeTeam = codeTeam;
        this.groupId = groupId;
    }



}
