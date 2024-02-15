package com.spring2.Models.Team.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private String codeTeam;

    public TeamEntity(String name,String codeTeam) {
        this.name = name;
        this.codeTeam=codeTeam;
    }
}

