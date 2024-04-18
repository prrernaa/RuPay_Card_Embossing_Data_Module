package com.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.card.entity.InactiveUser;
import com.card.entity.UserInfo;
import com.card.repo.InactiveUserRepository;
import com.card.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InactiveUserRepository inactiveUserRepository;


    @GetMapping
    public List<UserInfo> getAllUsers() {
    	List<UserInfo> allUsers = new ArrayList<>();
        List<UserInfo> activeUsers = userRepository.findAll();
        List<UserInfo> inactiveUsers = new ArrayList<>();
        
        // Convert InactiveUser objects to UserInfo objects
        for (InactiveUser inactiveUser : inactiveUserRepository.findAll()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(inactiveUser.getUsername());
            userInfo.setEmail(inactiveUser.getEmail());
            userInfo.setMobileNum(inactiveUser.getMobileNum());
            userInfo.setUserRole(inactiveUser.getUserrole());
            userInfo.setActive(0);
            userInfo.setRemark(inactiveUser.getRemark());
            // Set other properties as needed
            inactiveUsers.add(userInfo);
        }

        allUsers.addAll(activeUsers);
        allUsers.addAll(inactiveUsers);
        return allUsers;
    }
    
    @GetMapping("/active")
    public List<UserInfo> getActiveUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/inactive")
    public List<UserInfo> getInactiveUsersWithRoles() {
        List<UserInfo> inactiveUsersWithRoles = new ArrayList<>();

        // Retrieve inactive users from the InactiveUserRepository
        List<InactiveUser> inactiveUsers = inactiveUserRepository.findAll();

        // Populate user roles for inactive users
        inactiveUsers.forEach(inactiveUser -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(inactiveUser.getUsername());
            userInfo.setEmail(inactiveUser.getEmail());
            userInfo.setMobileNum(inactiveUser.getMobileNum());
            userInfo.setUserRole(inactiveUser.getUserrole()); // Populate user role for inactive user
            userInfo.setActive(0);
            userInfo.setRemark(inactiveUser.getRemark());
            inactiveUsersWithRoles.add(userInfo);
        });

        return inactiveUsersWithRoles;
    }
    
    @GetMapping("/{username}") // Corrected mapping for fetching user by username
    public ResponseEntity<UserInfo> getUserByUsername(@PathVariable String username) {
        UserInfo user = userRepository.findByUsername(username);
        
        System.out.println(user.getUsername());
        return ResponseEntity.ok(user);
    }


    @PostMapping("/{username}/edit")
    public void editUser(@PathVariable String username, @RequestParam String status, @RequestParam String remark) {
        UserInfo user = userRepository.findByUsername(username);
        if (user != null) {
            String editorUsername = getCurrentUsername();
            String updatedRemark;
            if (status.equals("active")) {
                user.setActive(1);
                updatedRemark = "Activated by " + editorUsername + ". " + remark;
            } else if (status.equals("inactive")) {
                user.setActive(0);
                updatedRemark = "Deactivated by " + editorUsername + ". " + remark;
                user.setRemark(updatedRemark);
                userRepository.save(user);
                InactiveUser inactiveUser = new InactiveUser(user.getUsername(), user.getEmail(), user.getMobileNum(), user.getRemark(), user.getUserRole());
                inactiveUserRepository.save(inactiveUser);
                userRepository.delete(user);
                
                
            } else {
                throw new IllegalArgumentException("Invalid status");
            }
            
            
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
