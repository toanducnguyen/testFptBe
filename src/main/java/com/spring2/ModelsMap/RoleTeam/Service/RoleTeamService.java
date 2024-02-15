package com.spring2.ModelsMap.RoleTeam.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.Models.Role.Entity.RoleEntity;
import com.spring2.Models.Role.Repository.RoleRepo;
import com.spring2.ModelsMap.RoleTeam.Entity.RoleTeamEntity;
import com.spring2.ModelsMap.RoleTeam.Repository.RoleTeamRepository;
import com.spring2.Models.Team.Entity.TeamEntity;
import com.spring2.Models.Team.Repository.TeamRepo;
import com.spring2.ModelsMap.UserRole.Service.UserRoleService;
import com.spring2.ModelsMap.userTeam.Entity.UserTeamEntity;
import com.spring2.ModelsMap.userTeam.Repository.UserTeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleTeamService {

    private RoleTeamRepository roleTeamRepository;
    private RoleRepo roleRepo;
    private TeamRepo teamRepo;
    private UserRoleService userRoleService;
    private UserTeamRepository userTeamRepository;
    private Authorization authorization;

    public void addRoleToTeam(String userCheckId, Input input){
        String roleIdCheck = String.valueOf(115);
        String roleName ="addRoleToTeam";
        Optional<RoleEntity> roleEntity1 = roleRepo.findByRoleName(roleName);
        RoleEntity role=roleEntity1.get();
        
        if (!authorization.check(userCheckId,role.getRoleId())){
            throw new Error("khong co quyen truy cap");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        Optional<RoleTeamEntity> roleTeamEntity = roleTeamRepository.findByCodeTeamAndRoleId(input.getCodeTeam(),input.getRoleId());
        if (roleTeamEntity.isPresent()){
            throw new Error("nhom nguoi dung da co quyen tren");
        }
        RoleTeamEntity roleTeam = new RoleTeamEntity(input.getCodeTeam(),input.getRoleId());
        roleTeamRepository.save(roleTeam);
        //
        List<UserTeamEntity> userTeamEntities =userTeamRepository.findAllByCodeTeam(input.getCodeTeam());
        for (UserTeamEntity u :userTeamEntities){
            input.setUserId(u.getUserId());
            userRoleService.addRoleToUser("Boss",input);
        }
    }
    public void deleteRoleFromTeam(String userCheckId,Input input) {
        String roleIdCheck = String.valueOf(116);
        if (!authorization.check(userCheckId, roleIdCheck)) {
            throw new Error("khong co quyen truy cap");
        }
        Optional<RoleEntity> roleEntity = roleRepo.findByRoleId(input.getRoleId());
        if (roleEntity.isEmpty()){
            throw new Error("khong ton tai quyen tren");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        Optional<RoleTeamEntity> roleTeamEntity = roleTeamRepository.findByCodeTeamAndRoleId(input.getCodeTeam(),input.getRoleId());
        if (roleTeamEntity.isEmpty()){
            throw new Error("nhom nguoi khong ton tai quyen tren quyen tren");
        }
        RoleTeamEntity roleTeam = roleTeamEntity.get();
        roleTeamRepository.delete(roleTeam);
        List<UserTeamEntity> userTeamEntities =userTeamRepository.findAllByCodeTeam(input.getCodeTeam());
        for (UserTeamEntity u :userTeamEntities){
            input.setUserId(u.getUserId());
            userRoleService.deleteRoleFromUser("Boss",input);
        }
    }
}
