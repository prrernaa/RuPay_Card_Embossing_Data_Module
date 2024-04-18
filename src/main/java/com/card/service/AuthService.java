package com.card.service;

import org.springframework.stereotype.Service;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;

@Service
public interface AuthService {
	
	
	public SignupInfo registerUser(SignupInfo signupInfo);
    public UserInfo authenticateUser(String username, String password);
	

}
