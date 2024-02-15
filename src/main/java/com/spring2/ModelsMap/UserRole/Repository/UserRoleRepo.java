package com.spring2.ModelsMap.UserRole.Repository;


import com.spring2.ModelsMap.UserRole.Entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleEntity,Long> {

    Optional<UserRoleEntity> findByUserIdAndRoleId(String userId, String roleId);
}
