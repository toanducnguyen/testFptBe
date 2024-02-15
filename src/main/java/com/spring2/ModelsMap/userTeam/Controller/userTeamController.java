package com.spring2.ModelsMap.userTeam.Controller;

import com.spring2.Input.Input;
import com.spring2.ModelsMap.userTeam.Service.userTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class userTeamController {
    @Autowired
    private userTeamService userTeamService;

    @PostMapping(value = "/addUserToTeam")
    public ResponseEntity<?> addUserToTeam(@RequestHeader String userIdCheck,
                                           @RequestBody Input input){
        try {
            userTeamService.addUserToTeam(userIdCheck, input);
            return ResponseEntity.ok("add user to team successfulluy");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/deleteUserFromTeam")
    public ResponseEntity<?> deleteUserFromTeam(@RequestHeader String userIdCheck,
                                           @RequestBody Input input){
        try {
            userTeamService.deleteUserFromTeam(userIdCheck,input);
            return ResponseEntity.ok("delete user from team successfulluy");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
