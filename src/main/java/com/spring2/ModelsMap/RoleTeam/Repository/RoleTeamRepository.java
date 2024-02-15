package com.spring2.ModelsMap.RoleTeam.Repository;

import com.spring2.ModelsMap.RoleTeam.Entity.RoleTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleTeamRepository extends JpaRepository<RoleTeamEntity,Long> {
    Optional<RoleTeamEntity> findByCodeTeamAndRoleId(String codeTeam, String roleId);

    List<RoleTeamEntity> findAllByCodeTeam(String codeTeam);
}
