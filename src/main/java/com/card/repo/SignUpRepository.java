package com.card.repo;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.card.entity.SignupInfo;
import com.card.entity.UserInfo;
import com.card.projection.SignupProjection;

import java.util.List;


@Repository
public interface SignUpRepository extends JpaRepository<SignupInfo, Integer> {
	//List findByUsername(String username);
	//UserInfo findByUsername(String username);
    Boolean existsByUsername(String username);
    	
	SignupInfo findByUsername(String username);
	
	void deleteByUsername(String username);
   
	@Query("SELECT NEW com.card.projection.SignupProjection(s.username, s.email, s.userRole, s.mobileNum) FROM SignupInfo s WHERE s.approvalStatus IS NULL")
    List<SignupProjection> findPendingSignups();
}
	