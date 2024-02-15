package com.spring2.Models.Group.Controller;


import com.spring2.Models.Group.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping(value = "/createGroup")
    public ResponseEntity<?> createGroup(@RequestHeader("userId") String userId, @RequestBody GroupService.Input input){
        try {
            groupService.createGroup(input,userId);
            return ResponseEntity.ok("create group successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping(value = "/getAllGroup")
    public ResponseEntity<?> getAllGroup() {
        try {
            return ResponseEntity.ok(groupService.getAllGroup());
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/deleteGroup")
    public ResponseEntity<?> deleteGroup(@RequestHeader("userId") String userId, @RequestBody String groupId){
        try {
            groupService.deleteGroup(groupId,userId);
            return ResponseEntity.ok("delete group successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
