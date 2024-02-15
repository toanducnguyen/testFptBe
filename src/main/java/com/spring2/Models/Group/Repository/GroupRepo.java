package com.spring2.Models.Group.Repository;

import com.spring2.Models.Group.Entity.GroupEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<GroupEntity,Long> {



    Optional<GroupEntity> findByGroupId(String groupId);

    Optional<GroupEntity> findByNameGroupAndGroupId(String nameGroup, String groupId);
}
