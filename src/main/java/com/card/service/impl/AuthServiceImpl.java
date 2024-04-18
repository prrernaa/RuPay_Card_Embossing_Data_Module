package com.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;
import com.card.exception.UsernameAlreadyExistsException;
import com.card.repo.SignUpRepository;
import com.card.repo.UserRepository;
import com.card.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private SignUpRepository signuprepository;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SignupInfo registerUser(SignupInfo signupInfo) {
   	
    	// Check if the username is already taken
        if (signuprepository.existsByUsername(signupInfo.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        // You may want to perform additional validation here
        
        // Map UserInfo to User entity
        SignupInfo user = new SignupInfo();
        user.setUsername(signupInfo.getUsername());
        user.setEmail(signupInfo.getEmail());
        user.setPassword(passwordEncoder.encode(signupInfo.getPassword()));

        user.setUserRole(signupInfo.getUserRole());
        user.setMobileNum(signupInfo.getMobileNum());
        // Save the new user
        SignupInfo savedUser = signuprepository.save(user);

        // Return the saved user information
        SignupInfo newUser = new SignupInfo();
        newUser.setId(savedUser.getId());
        newUser.setUsername(savedUser.getUsername());
        newUser.setPassword(savedUser.getPassword());
        newUser.setEmail(savedUser.getEmail()); 
        newUser.setUserRole(savedUser.getUserRole());
        newUser.setMobileNum(savedUser.getMobileNum());
        
        
        newUser.setPassword(passwordEncoder.encode(signupInfo.getPassword()));

        return newUser;
    }


    @Override
    public UserInfo authenticateUser(String username, String password) {
        UserInfo user = (UserInfo) userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null; // Authentication failed
    }
}
