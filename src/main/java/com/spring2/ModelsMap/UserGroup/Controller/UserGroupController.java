package com.spring2.ModelsMap.UserGroup.Controller;

import com.spring2.Input.Input;
import com.spring2.ModelsMap.UserGroup.Service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;


    @PostMapping(value = "/addGroupRToUser")
    public ResponseEntity<?> addGroupRToUser(@RequestHeader String userCheckId,
                                             @RequestBody Input  input){
        try {
            userGroupService.addGroupRToUser(userCheckId,input);
            return ResponseEntity.ok("add group to user successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/deleteGroupRFromUser")
    public ResponseEntity<?> deleteGroupRFromUser(@RequestHeader String userCheckId,
                                             @RequestBody Input input){
        try {
            userGroupService.deleteGroupRFromUser(userCheckId,input);
            return ResponseEntity.ok("delete group from user successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
