package com.spring2.Models.User.Repository;

import com.spring2.Models.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserId(String userId);
}
