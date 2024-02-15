package com.spring2.ModelsMap.userTeam.Repository;

import com.spring2.ModelsMap.userTeam.Entity.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeamEntity,Long> {

    Optional<UserTeamEntity> findByUserIdAndCodeTeam(String userId, String codeTeam);

    List<UserTeamEntity> findAllByCodeTeam(String codeTeam);
}
