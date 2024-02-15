package com.spring2.Models.Team.Repository;

import com.spring2.Models.Team.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<TeamEntity,Long> {

    Optional<TeamEntity> findByCodeTeam(String name);
}
