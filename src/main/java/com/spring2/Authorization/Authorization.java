package com.spring2.Authorization;



import com.spring2.ModelsMap.UserRole.Entity.UserRoleEntity;
import com.spring2.ModelsMap.UserRole.Repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class Authorization {
    @Autowired
    private UserRoleRepo userRoleRepo;

    public boolean check(String  userId,String roleId){
        Optional<UserRoleEntity> userRoleEntity = userRoleRepo.findByUserIdAndRoleId(userId,roleId);
        if (userRoleEntity.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
}
