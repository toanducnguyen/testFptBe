package com.spring2.Models.Team.Controller;

import com.spring2.Models.Team.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;



    @PostMapping(value = "/createTeam")
    public ResponseEntity<?> createTeam(@RequestHeader("userId") String userId, @RequestBody TeamService.Input input) {
        try {
            teamService.createTeam(input,userId);
            return ResponseEntity.ok("create team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping(value = "/getAllTeam")
    public ResponseEntity<?> getAllTeam() {
        try {
            return ResponseEntity.ok(teamService.getAllTeam());
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @DeleteMapping(value = "/deleteTeam")
    public ResponseEntity<?> deleteTeam(@RequestHeader("userId") String userId, @RequestBody String codeTeam) {
        try {
            teamService.deleteTeam(codeTeam,userId);
            return ResponseEntity.ok("delete team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
