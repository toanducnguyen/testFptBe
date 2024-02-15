package com.spring2.Models.Group.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "GroupR")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String groupId;
    @Column
    private String nameGroup;

    public GroupEntity(String groupId, String nameGroup) {
        this.groupId = groupId;
        this.nameGroup = nameGroup;
    }


}

