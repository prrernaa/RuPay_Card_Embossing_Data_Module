package com.card.config;
 import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.card.entity.UserInfo;

public  class CustomUserDetails implements UserDetails {
	private UserInfo userInfo;

	
	
	public CustomUserDetails(UserInfo userInfo) {
		super();
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfo.getUserRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	@Override
    public boolean isEnabled() {
        // Check if the user is active (active status is 1)
        return userInfo.getActive() == 1;
    }
	
	
	
}

