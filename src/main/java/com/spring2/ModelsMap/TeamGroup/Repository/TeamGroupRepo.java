package com.spring2.ModelsMap.TeamGroup.Repository;

import com.spring2.ModelsMap.TeamGroup.Entity.TeamGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamGroupRepo extends JpaRepository<TeamGroupEntity,Long> {
    Optional<TeamGroupEntity> findByCodeTeamAndGroupId(String codeTeam, String groupId);
}
