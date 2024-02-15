package com.spring2.ModelsMap.userTeam.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="UserTeam")
public class UserTeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userId;
    @Column
    private String codeTeam;

    public UserTeamEntity(String userId, String codeTeam) {
        this.userId = userId;
        this.codeTeam = codeTeam;
    }

}
