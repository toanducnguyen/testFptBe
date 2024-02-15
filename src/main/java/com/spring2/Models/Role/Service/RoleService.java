package com.spring2.Models.Role.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Models.Role.Entity.RoleEntity;

import com.spring2.Models.Role.Repository.RoleRepo;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepo roleRepo;
    private Authorization authorization;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        private String roleName;
        private String roleId;

    }
    public void createRole(Input input,String userId){
        String roleId = "105";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (!roleEntity.isEmpty()){
            throw new Error("da ton tai quyen tren");
        }
        RoleEntity roleEntity1 = new RoleEntity(input.getRoleName(),input.getRoleId());
        roleRepo.save(roleEntity1);
    }
    public List<RoleEntity> getAllRole(){
        List<RoleEntity> roleEntities = roleRepo.findAll();
        return roleEntities;
    }
    public void deleteRole(String roleId,String userId){
        String roleCheckId = "106";
        if (!authorization.check(userId,roleCheckId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(roleId);
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        RoleEntity roleEntity1 = roleEntity.get();
        roleRepo.delete(roleEntity1);
    }
}
