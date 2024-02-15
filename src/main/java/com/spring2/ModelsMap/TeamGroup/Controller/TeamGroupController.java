package com.spring2.ModelsMap.TeamGroup.Controller;

import com.spring2.Input.Input;
import com.spring2.ModelsMap.TeamGroup.Service.TeamGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamGroupController {
    @Autowired
    private TeamGroupService teamGroupService;


    @PostMapping(value = "addGroupRToTeam")
    public ResponseEntity<?>addGroupRToTeam(@RequestHeader String userCheckId,
                                            @RequestBody Input input){
        try {
            teamGroupService.addGroupToTeam(userCheckId,input);
            return ResponseEntity.ok("add group to team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "deleteGroupRFromTeam")
    public ResponseEntity<?>deleteGroupRFromTeam(@RequestHeader String userCheckId,
                                            @RequestBody Input input){
        try {
            teamGroupService.deleteGroupFromTeam(userCheckId,input);
            return ResponseEntity.ok("delete group from team successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
