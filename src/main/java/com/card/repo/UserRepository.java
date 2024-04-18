package com.card.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.card.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	
    Boolean existsByUsername(String username);
    
    @Query("SELECT user FROM UserInfo user WHERE user.username = :username")
    public UserInfo findByUsername(@Param("username") String username);

}
