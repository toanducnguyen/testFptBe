package com.spring2.ModelsMap.RoleTeam.Controller;

import com.spring2.Input.Input;
import com.spring2.ModelsMap.RoleTeam.Service.RoleTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleTeamController {
    @Autowired
    private RoleTeamService roleTeamService;

    @PostMapping(value ="/addRoleToTeam")
    public ResponseEntity<?> addRoleToTeam(@RequestHeader String userId,
                                           @RequestBody Input input){
        try {
            roleTeamService.addRoleToTeam(userId, input);
            return ResponseEntity.ok("add role to team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value ="/deleteRoleFromTeam")
    public ResponseEntity<?> deleteRoleFromTeam(@RequestHeader String userId,
                                           @RequestBody Input input){
        try {
            roleTeamService.deleteRoleFromTeam(userId,input);
            return ResponseEntity.ok("delete role from team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
