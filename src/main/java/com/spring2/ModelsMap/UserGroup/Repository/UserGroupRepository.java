package com.spring2.ModelsMap.UserGroup.Repository;

import com.spring2.ModelsMap.UserGroup.Entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupEntity,Long> {
    Optional<UserGroupEntity> findByUserIdAndGroupId(String userId, String groupId);
}
