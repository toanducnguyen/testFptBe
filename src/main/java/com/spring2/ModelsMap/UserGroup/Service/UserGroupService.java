package com.spring2.ModelsMap.UserGroup.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.Models.Group.Entity.GroupEntity;
import com.spring2.Models.Group.Repository.GroupRepo;
import com.spring2.Models.User.Entity.UserEntity;
import com.spring2.Models.User.Repository.UserRepo;
import com.spring2.ModelsMap.RoleGroup.Entity.RoleGroupREntity;
import com.spring2.ModelsMap.RoleGroup.Repository.RoleGroupRRepository;
import com.spring2.ModelsMap.UserGroup.Entity.UserGroupEntity;
import com.spring2.ModelsMap.UserGroup.Repository.UserGroupRepository;
import com.spring2.ModelsMap.UserRole.Service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserGroupService {
    private UserGroupRepository userGroupRepository;
    private GroupRepo groupRepo;
    private UserRepo userRepo;
    private UserRoleService userRoleService;
    private RoleGroupRRepository roleGroupRRepository;
    private Authorization authorization;

    public void addGroupRToUser(String userCheckId, Input input){
        String roleIdCheck = String.valueOf(119);
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
        if (user.isEmpty()){
            throw new Error("nguoi dung khong ton tai");
        }
        Optional<UserGroupEntity> userGroupEntity = userGroupRepository.findByUserIdAndGroupId(input.getUserId(),input.getGroupId());
        if (userGroupEntity.isPresent()){
            throw new Error("nguoi dung da co nhom quyen tren");
        }
        UserGroupEntity userGroup = new UserGroupEntity(input.getUserId(),input.getGroupId());
        userGroupRepository.save(userGroup);
        //add quyen tu nhom quyen vao nguoi dung
        List<RoleGroupREntity> roleGroupREntities = roleGroupRRepository.findAllByGroupId(input.getGroupId());
        for (RoleGroupREntity rg :roleGroupREntities){
            input.setRoleId(rg.getRoleId());
            userRoleService.addRoleToUser("Boss",input);
        }
    }
    public void deleteGroupRFromUser(String userCheckId,Input input){
        String roleIdCheck = String.valueOf(120);
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
        if (user.isEmpty()){
            throw new Error("nguoi dung khong ton tai");
        }
        Optional<UserGroupEntity> userGroupEntity = userGroupRepository.findByUserIdAndGroupId(input.getUserId(), input.getGroupId());
        if (userGroupEntity.isPresent()){
            throw new Error("nguoi dung khong ton tai nhom quyen tren");
        }
        UserGroupEntity userGroup = userGroupEntity.get();
        userGroupRepository.delete(userGroup);
        //xoa quyen tu nhom quyen vao nguoi dung
        List<RoleGroupREntity> roleGroupREntities = roleGroupRRepository.findAllByGroupId(input.getGroupId());
        for (RoleGroupREntity rg :roleGroupREntities){
            input.setRoleId(rg.getRoleId());
            userRoleService.deleteRoleFromUser("Boss",input);
        }
    }
}
