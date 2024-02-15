package com.spring2.ModelsMap.TeamGroup.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.Models.Group.Entity.GroupEntity;
import com.spring2.Models.Group.Repository.GroupRepo;
import com.spring2.Models.Team.Entity.TeamEntity;
import com.spring2.Models.Team.Repository.TeamRepo;
import com.spring2.ModelsMap.TeamGroup.Entity.TeamGroupEntity;
import com.spring2.ModelsMap.TeamGroup.Repository.TeamGroupRepo;
import com.spring2.ModelsMap.UserGroup.Service.UserGroupService;
import com.spring2.ModelsMap.userTeam.Entity.UserTeamEntity;
import com.spring2.ModelsMap.userTeam.Repository.UserTeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamGroupService {
    private TeamGroupRepo teamGroupRepo;
    private Authorization authorization;
    private TeamRepo teamRepo;
    private GroupRepo groupRepo;
    private UserTeamRepository userTeamRepository;
    private UserGroupService userGroupService;

    public void addGroupToTeam(String userCheckId, Input input){
        String roleIdCheck = String.valueOf(121);
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<TeamGroupEntity> teamGroupEntity = teamGroupRepo.findByCodeTeamAndGroupId(input.getCodeTeam(), input.getGroupId());
        if (teamGroupEntity.isPresent()){
            throw new Error("da ton tai nhom quyen trong nhom nguoi dung tren");
        }
        TeamGroupEntity teamGroup = new TeamGroupEntity(input.getCodeTeam(),input.getGroupId());
        teamGroupRepo.save(teamGroup);
        List<UserTeamEntity> userTeamEntities =userTeamRepository.findAllByCodeTeam(input.getCodeTeam());
        for (UserTeamEntity u :userTeamEntities){
            input.setUserId(u.getUserId());
            userGroupService.addGroupRToUser("Boss",input);

        }
    }
    public void deleteGroupFromTeam(String userCheckId,Input input){
        String roleIdCheck = String.valueOf(122);
        if (!authorization.check(userCheckId,roleIdCheck)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        Optional<GroupEntity> groupEntity = groupRepo.findByGroupId(input.getGroupId());
        if (groupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen tren");
        }
        Optional<TeamGroupEntity> teamGroupEntity = teamGroupRepo.findByCodeTeamAndGroupId(input.getCodeTeam(), input.getGroupId());
        if (teamGroupEntity.isEmpty()){
            throw new Error("khong ton tai nhom quyen trong nhom nguoi dung tren");
        }
        TeamGroupEntity teamGroup = teamGroupEntity.get();
        teamGroupRepo.delete(teamGroup);
        List<UserTeamEntity> userTeamEntities =userTeamRepository.findAllByCodeTeam(input.getCodeTeam());
        for (UserTeamEntity u :userTeamEntities){
            input.setUserId(u.getUserId());
            userGroupService.deleteGroupRFromUser("Boss",input);
        }
    }
}
