package com.spring2.ModelsMap.RoleGroup.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.Models.Group.Entity.GroupEntity;
import com.spring2.Models.Group.Repository.GroupRepo;
import com.spring2.Models.Role.Entity.RoleEntity;
import com.spring2.Models.Role.Repository.RoleRepo;
import com.spring2.ModelsMap.RoleGroup.Entity.RoleGroupREntity;
import com.spring2.ModelsMap.RoleGroup.Repository.RoleGroupRRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleGroupRService {
    private RoleGroupRRepository roleGroupRRepository;
    private RoleRepo roleRepo;
    private GroupRepo groupRepo;
    private Authorization authorization;

    public void addRoleToGroupR(String userCheckId,Input input){
        String roleIdCheck = "117";
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        Optional<RoleGroupREntity> roleGroupREntity = roleGroupRRepository.findByRoleIdAndGroupId(input.getRoleId(),input.getGroupId());
        if (roleGroupREntity.isPresent()){
            throw new Error("da ton tai quyen o trong nhom quyen tren");
        }

        RoleGroupREntity roleGroupR = new RoleGroupREntity(input.getRoleId(),input.getGroupId());
        roleGroupRRepository.save(roleGroupR);
    }
    public void deleteRoleFromGroupR(String userCheckId, Input input){
        String roleIdCheck = String.valueOf(118);
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (!groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        Optional<RoleGroupREntity> roleGroupREntity = roleGroupRRepository.findByRoleIdAndGroupId(input.getRoleId(),input.getGroupId());
        if (roleGroupREntity.isEmpty()){
            throw new Error("khong ton tai quyen o trong nhom quyen tren");
        }
        RoleGroupREntity roleGroupR = roleGroupREntity.get();
        roleGroupRRepository.delete(roleGroupR);
    }
}
