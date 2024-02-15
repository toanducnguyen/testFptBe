package com.spring2.ModelsMap.RoleGroup.Repository;

import com.spring2.ModelsMap.RoleGroup.Entity.RoleGroupREntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleGroupRRepository extends JpaRepository<RoleGroupREntity,Long> {
    Optional<RoleGroupREntity> findByRoleIdAndGroupId(String roleId, String groupId);

    List<RoleGroupREntity> findAllByGroupId(String groupId);
}
