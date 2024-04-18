package com.card.service.impl;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.card.config.CustomUserDetails;
import com.card.entity.UserInfo;
import com.card.repo.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository; 
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo userInfo= userRepository.findByUsername(username);
		if (userInfo==null)
		{
			throw new UsernameNotFoundException(" Couldn't fine the User");
			
		}
		return new CustomUserDetails(userInfo);
		
	}

}
