package com.spring2.Models.Team.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Models.Team.Entity.TeamEntity;
import com.spring2.Models.Team.Repository.TeamRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {
    private TeamRepo teamRepo;
    private Authorization authorization;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        private String name;
        private  String codeTeam;
    }

    public void createTeam(Input input,String userId){
        String roleId = "107";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (!team.isEmpty()){
            throw new Error("da ton tai nhom nguoi dung tren");
        }
        TeamEntity teamEntity = new TeamEntity(input.getName(),input.getCodeTeam());
        teamRepo.save(teamEntity);
    }
    public List<TeamEntity> getAllTeam(){
        List<TeamEntity> teamEntities = teamRepo.findAll();
        return teamEntities;
    }

    public void updateTeam(Input input){
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(input.getCodeTeam());
        if (team.isEmpty()){
            throw new Error("khong ton tai nhom nguoi dung tren");
        }
        TeamEntity teamEntity = team.get();
        if(input.name != null){
            teamEntity.setName(input.name);
        }
        if (input.codeTeam!= null){
            teamEntity.setCodeTeam(input.codeTeam);
        }
        teamRepo.save(teamEntity);
    }
    public void deleteTeam(String codeTeam ,String userId){
        String roleId = "108";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<TeamEntity> team = teamRepo.findByCodeTeam(codeTeam);
        if (team.isEmpty()){
            throw new Error("k tim thay nhom nguoi dung");
        }
        TeamEntity team1 = team.get();
        teamRepo.delete(team1);
    }
}
