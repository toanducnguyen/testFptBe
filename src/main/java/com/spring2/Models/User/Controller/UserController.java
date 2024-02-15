package com.spring2.Models.User.Controller;

import com.spring2.Models.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> createUser(@RequestHeader String userId,@RequestBody UserService.Input input) {
        try {
            userService.createUser(input, userId);
            return ResponseEntity.ok("Create successfully");
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping(value = "/getAllUser")
    public ResponseEntity<?> getAllUser(@RequestHeader String userIdCheck) {
        try {
            return  ResponseEntity.ok(userService.getAll(userIdCheck));
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestHeader String userIdCheck,@RequestBody String userId) {
        try {
            userService.deleteUser(userIdCheck,userId);
            return ResponseEntity.ok("delete user successfully");
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
