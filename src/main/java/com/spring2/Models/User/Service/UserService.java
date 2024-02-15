package com.spring2.Models.User.Service;

import com.spring2.Authorization.Authorization;
import com.spring2.Models.User.Entity.UserEntity;
import com.spring2.Models.User.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;
    private Authorization authorization;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        private String name;
        private String userId;
    }

    public  void createAdmin(Input input ,String userId){
        String roleId = "102";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserEntity> admin = userRepo.findByUserId(input.getUserId());
        if (!admin.isEmpty()){
            throw new Error("admin da ton tai");
        }
        UserEntity adminNew = new UserEntity(input.getName(), input.getUserId(),true);
        userRepo.save(adminNew);
    }
    public void createUser(Input input,String userId) {
        String roleId = "101";
        if (!authorization.check(userId,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserEntity> user = userRepo.findByUserId(input.getUserId());
        if (!user.isEmpty()){
            throw new Error("nguoi dung da ton tai");
        }
        UserEntity userNew = new UserEntity(input.getName(), input.getUserId(),false);
        userRepo.save(userNew);
    }
    public void updateUser(Input input,String userId){

        Optional<UserEntity> userInfoOpt = userRepo.findByUserId(userId);
        if(userInfoOpt.isEmpty()) {
            throw new Error("not found user");
        }
        UserEntity userInfo = userInfoOpt.get();
        if(input.name != null){
            userInfo.setName(input.name);
        }
        if (input.userId != null){
            userInfo.setUserId(input.userId);
        }
        userRepo.save(userInfo);
    }
    public void deleteUser(String userIdCheck,String userId){
        String roleId = "103";
        if (!authorization.check(userIdCheck,roleId)){ 
            throw new Error("khong co quyen truy cap");
        }
        Optional<UserEntity> user = userRepo.findByUserId(userId);
        if(user.isEmpty()) {
            throw new Error("not found user ");
        }
        UserEntity userOne = user.get();
        userRepo.delete(userOne);
    }
    public List<UserEntity> getAll(String userIdCheck){
        String roleId ="127";
        if (!authorization.check(userIdCheck,roleId)){
            throw new Error("khong co quyen truy cap");
        }
        List<UserEntity> userEntitys = userRepo.findAll();
        return userEntitys;
    }

}
