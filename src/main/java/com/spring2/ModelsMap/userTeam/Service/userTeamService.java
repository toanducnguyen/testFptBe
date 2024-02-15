package com.spring2.ModelsMap.userTeam.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Input.Input;
import com.spring2.ModelsMap.RoleTeam.Entity.RoleTeamEntity;
import com.spring2.ModelsMap.RoleTeam.Repository.RoleTeamRepository;

import com.spring2.Models.Team.Entity.TeamEntity;
import com.spring2.Models.Team.Repository.TeamRepo;
import com.spring2.Models.User.Entity.UserEntity;
import com.spring2.Models.User.Repository.UserRepo;
import com.spring2.ModelsMap.UserRole.Service.UserRoleService;
import com.spring2.ModelsMap.userTeam.Entity.UserTeamEntity;
import com.spring2.ModelsMap.userTeam.Repository.UserTeamRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class userTeamService {
    private UserTeamRepository userTeamRepository;
    private UserRepo userRepo;
    private TeamRepo teamRepo;
    private RoleTeamRepository roleTeamRepository;
    private UserRoleService userRoleService;
    private Authorization authorization;


    public void addUserToTeam(String userIdCheck, Input input){
        String roleId = String.valueOf(113);
        if (!authorization.check(userIdCheck,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
        if (user.isEmpty()){
            throw new Error("nguoi dung khong ton tai");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        Optional<UserTeamEntity> userTeamEntity = userTeamRepository.findByUserIdAndCodeTeam(input.getUserId(),input.getCodeTeam());
        if (userTeamEntity.isPresent()){
            throw new Error("nguoi dung da co o trong team");
        }
        UserTeamEntity userTeam= new UserTeamEntity(input.getUserId(),input.getCodeTeam());
        userTeamRepository.save(userTeam);
        //add role cua team vao nguoi dung
        List<RoleTeamEntity> roleTeamEntity = roleTeamRepository.findAllByCodeTeam(input.getCodeTeam());
        for (RoleTeamEntity r : roleTeamEntity){
            input.setRoleId(r.getRoleId());
            userRoleService.addRoleToUser("Boss",input);
        }
    }

    public void deleteUserFromTeam(String userIdCheck,Input input){
        String roleId = String.valueOf(114);
        if (!authorization.check(userIdCheck,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserTeamEntity> userTeamEntity = userTeamRepository.findByUserIdAndCodeTeam(input.getUserId(),input.getCodeTeam());
        if (userTeamEntity.isEmpty()){
            throw new Error("khong ton tai");
        }
        UserTeamEntity userTeam=userTeamEntity.get();
        userTeamRepository.delete(userTeam);

        //xoa tat ca quyen cua team khoi user
        List<RoleTeamEntity> roleTeamEntity = roleTeamRepository.findAllByCodeTeam(input.getRoleId());
        for (RoleTeamEntity r : roleTeamEntity){
            input.setRoleId(r.getRoleId());
            userRoleService.deleteRoleFromUser("Boss",input);
        }
    }

//    public void updateUserTeam(Input input){
//        Optional<UserTeamEntity> userTeamEntity = userTeamRepository.findByUserIdAndCodeTeam(input.getUserId(),input.getCodeTeam());
//        if (userTeamEntity.isEmpty()){
//            throw new Error("khong ton tai");
//        }
//        UserTeamEntity userTeam=userTeamEntity.get();
//        if(input.userId != null){
//            Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
//            if (user.isEmpty()){
//                throw new Error("nguoi dung khong ton tai");
//            }
//            userTeam.setUserId(input.userId);
//        }
//        if(input.codeTeam != null){
//            Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
//            if (team.isEmpty()){
//                throw new Error("khong ton tai nhom nguoi dung tren");
//            }
//            userTeam.setCodeTeam(input.codeTeam);
//        }
//        userTeamRepository.save(userTeam);
//    }

}
