package com.spring2.ModelsMap.UserRole.Service;


import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.Models.Role.Entity.RoleEntity;
import com.spring2.Models.Role.Repository.RoleRepo;
import com.spring2.Models.User.Entity.UserEntity;
import com.spring2.Models.User.Repository.UserRepo;
import com.spring2.ModelsMap.UserRole.Entity.UserRoleEntity;
import com.spring2.ModelsMap.UserRole.Repository.UserRoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserRoleService {
    private UserRoleRepo userRoleRepo;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private Authorization authorization;

    public void addRoleToUser(String userCheckId, Input input){
        String roleIdCheck = String.valueOf(111);
        System.out.println(input.getUserId());
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserRoleEntity> userRoleEntity = userRoleRepo.findByUserIdAndRoleId(input.getUserId(),input.getRoleId());
        if (!userRoleEntity.isEmpty()){
            throw new Error("nguoi dung da co quyen tren");
        }
        Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
        if (user.isEmpty()){
            throw new Error("nguoi dung khong ton tai");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        UserRoleEntity userRole = new UserRoleEntity(input.getUserId(), input.getRoleId());
        userRoleRepo.save(userRole);
    }
    public void deleteRoleFromUser(String userCheckId,Input input) {
        String roleIdCheck = String.valueOf(112);
        if (!authorization.check(userCheckId, roleIdCheck)) {
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserRoleEntity> userRoleEntity = userRoleRepo.findByUserIdAndRoleId(input.getUserId(),input.getRoleId());
        if (userRoleEntity.isEmpty()) {
            throw new Error("khong ton tai nguoi dung co quyen tren");
        }
        UserRoleEntity userRole = userRoleEntity.get();
        userRoleRepo.delete(userRole);
    }
}
