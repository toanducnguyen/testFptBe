package com.spring2.Models.Role.Controller;


import com.spring2.Models.Role.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;


    @PostMapping(value = "/createRole")
    public ResponseEntity<?> createRole(@RequestHeader("userId") String userId, @RequestBody RoleService.Input input) {
        try {
            roleService.createRole(input,userId);
            return ResponseEntity.ok("create role successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping(value = "/getAllRole")
    public ResponseEntity<?> getAllRole() {
        try {
            return ResponseEntity.ok(roleService.getAllRole());
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @DeleteMapping(value = "/deleteRole")
    public ResponseEntity<?> deleteRole(@RequestHeader("userId") String userId, @RequestBody String roleId) {
        try {
            roleService.deleteRole(roleId,userId);
            return ResponseEntity.ok("delete role successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
