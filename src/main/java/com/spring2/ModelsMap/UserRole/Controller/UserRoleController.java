package com.spring2.ModelsMap.UserRole.Controller;


import com.spring2.Input.Input;
import com.spring2.ModelsMap.UserRole.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;


    @PostMapping(value = "/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestHeader("userCheckId") String userCheckId,
                                           @RequestBody Input input){

        try {
            userRoleService.addRoleToUser(userCheckId,input);
            return ResponseEntity.ok("add role to user successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/deleteRoleFromUser")
    public ResponseEntity<?> deleteRoleToUser(@RequestHeader String userCheckId,
                                             @RequestBody Input input){
        try {
            userRoleService.deleteRoleFromUser(userCheckId,input);
            return ResponseEntity.ok("delete role from user successfully");
        }catch (Throwable e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
