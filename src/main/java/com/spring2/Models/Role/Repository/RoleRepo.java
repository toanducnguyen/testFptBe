package com.spring2.Models.Role.Repository;

import com.spring2.Models.Role.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByRoleName(String roleName);

    Optional<RoleEntity> findByRoleId(String roleId);
}
