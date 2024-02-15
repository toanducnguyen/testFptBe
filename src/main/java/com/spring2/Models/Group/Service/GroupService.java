package com.spring2.Models.Group.Service;


import com.spring2.Authorization.Authorization;
import com.spring2.Models.Group.Entity.GroupEntity;
import com.spring2.Models.Group.Repository.GroupRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class GroupService {
    private GroupRepo groupRepo;
    private Authorization authorization;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        private String nameGroup;
        private String groupId;
    }
    public void createGroup(Input input,String userId){
        String roleId = "109";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (!groupEntity.isEmpty()){
            throw new Error("da ton tai nhom quyen tren");
        }
        GroupEntity group = new GroupEntity(input.getGroupId(),input.getNameGroup());
        groupRepo.save(group);
    }
    public List<GroupEntity> getAllGroup(){
        List<GroupEntity> groupEntities = groupRepo.findAll();
        return groupEntities;
    }
    public  void deleteGroup(String groupId,String userId){
        String roleCheckId = "110";
        if (!authorization.check(userId,roleCheckId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(groupId);
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        GroupEntity group = groupEntity.get();
        groupRepo.delete(group);
    }
}
