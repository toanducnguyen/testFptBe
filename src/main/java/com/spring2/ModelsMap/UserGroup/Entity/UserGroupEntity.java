package com.spring2.ModelsMap.UserGroup.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="UserGroup")
public class UserGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String userId;
    @Column
    private String groupId;

    public UserGroupEntity(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }
}
