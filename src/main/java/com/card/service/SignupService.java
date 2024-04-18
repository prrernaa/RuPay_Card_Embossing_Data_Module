package com.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;
import com.card.projection.SignupProjection;
import com.card.repo.SignUpInfoRepository;
import com.card.repo.SignUpRepository;
import com.card.repo.UserInfoRepository;

import java.util.List;

@Service
public class SignupService {

    @Autowired
    private SignUpRepository signupRepository;
    
    @Autowired
    private SignUpInfoRepository signUpInfoRepository;
   
    
    public List<SignupProjection> getPendingSignups() {
        return signupRepository.findPendingSignups();
    }

//    public void approveSignup(String username) {
//        SignupInfo signup = signupRepository.findByUsername(username);
//        if (signup != null) {
//            signup.setApprovalStatus(1); // Assuming 1 represents approved status
//            signupRepository.save(signup);
//            
//            UserInfo userInfo = new UserInfo();
//            
//            userInfo.setUsername(signup.getUsername()); // Assuming similar field names
//            userInfo.setEmail(signup.getEmail());
//            userInfo.setPassword(signup.getPassword());
//            userInfo.setUserRole(signup.getUserRole());
//            userInfo.setMobileNum(signup.getMobileNum());
//            userInfoRepository.save(userInfo);
//
//            // Delete the content from SignupInfo table
//            signupRepository.delete(signup);
//        }
//    }

//    public void rejectSignup(String username) {
//        SignupInfo signup = signupRepository.findByUsername(username);
//        if (signup != null) {
//            signupRepository.delete(signup);
//        }
//    }
    
    public boolean isUsernameExistsInSignupInfo(String username) {
        return signUpInfoRepository.existsByUsername(username);
    }
}

