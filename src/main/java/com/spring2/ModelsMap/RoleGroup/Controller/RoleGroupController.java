package com.spring2.ModelsMap.RoleGroup.Controller;

import com.spring2.Input.Input;
import com.spring2.ModelsMap.RoleGroup.Service.RoleGroupRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleGroupController {
    @Autowired
    private RoleGroupRService roleGroupRService;


    @PostMapping(value = "/addRoleToGroupR")
    public ResponseEntity<?> addRoleToGroup(@RequestHeader String userIdCheck,
                                            @RequestBody Input input){
        try {
            roleGroupRService.addRoleToGroupR(userIdCheck,input);
            return ResponseEntity.ok("add role to groupR successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/addRoleToGroupR")
    public ResponseEntity<?> deleteRoleFromGroup(@RequestHeader String userIdCheck,
                                            @RequestBody Input input){
        try {
            roleGroupRService.deleteRoleFromGroupR(userIdCheck,input);
            return ResponseEntity.ok("delete role from groupR successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
