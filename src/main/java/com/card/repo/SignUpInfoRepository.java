package com.card.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;

public interface SignUpInfoRepository extends JpaRepository<SignupInfo, Integer> {
    SignupInfo findByUsername(String username);
    boolean existsByUsername(String username);
}

