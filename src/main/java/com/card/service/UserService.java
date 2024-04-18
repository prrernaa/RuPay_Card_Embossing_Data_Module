package com.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;
import com.card.repo.SignUpInfoRepository;
import com.card.repo.UserInfoRepository;
import com.card.repo.UserRepository;

@Service
public class UserService {

    
    @Autowired 
    private UserInfoRepository userInfoRepository;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo != null) {
            // Use password encoder to check if the provided password matches the hashed password in the database
        	System.out.println("right pasword");
            return passwordEncoder.matches(password, userInfo.getPassword());
        }
        System.out.println("wrong pasword");
        return false;
    }
    
    public String getUserRole(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo != null) {
            return userInfo.getUserRole(); // Assuming there's a method to get the role from UserInfo entity
        }
        return null;
    }
   

	public boolean isUsernameExistsInUserInfo(String username) {
		
		return userInfoRepository.existsByUsername(username);
	}
}

//
//package com.card.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.card.entity.SignupInfo;
//import com.card.entity.UserInfo;
//import com.card.repo.SignUpInfoRepository;
//import com.card.repo.UserInfoRepository;
//import com.card.repo.UserRepository;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    
//    @Autowired 
//    private UserInfoRepository userInfoRepository;
//    
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public boolean login(String username, String password) {
//        UserInfo userInfo = userInfoRepository.findByUsername(username);
//        if (userInfo != null) {
//            // Use password encoder to check if the provided password matches the hashed password in the database
//        	System.out.println("Correct pasword");
//            return passwordEncoder.matches(password, userInfo.getPassword());
//        }
//        System.out.println("wrong pasword");
//        return false;
//    }
//    
//    public String getUserRole(String username) {
//        UserInfo userInfo = userInfoRepository.findByUsername(username);
//        if (userInfo != null) {
//            return userInfo.getUserRole(); // Assuming there's a method to get the role from UserInfo entity
//        }
//        return null;
//    }
//   
//
//	public boolean isUsernameExistsInUserInfo(String username) {
//		
//		return userInfoRepository.existsByUsername(username);
//	}
//	
//	
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		throw new UsernameNotFoundException("User not found with username: " + username);
//	}
//}

