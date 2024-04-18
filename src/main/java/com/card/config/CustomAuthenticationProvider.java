package com.card.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.card.service.SignupService;
import com.card.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private SignupService signupService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        boolean usernameExistsInUserInfo = userService.isUsernameExistsInUserInfo(username);
        boolean usernameExistsInSignupInfo = signupService.isUsernameExistsInSignupInfo(username);

        if (!usernameExistsInUserInfo && !usernameExistsInSignupInfo) {
            throw new UsernameNotFoundException("Please sign up first");
        } else if (usernameExistsInSignupInfo) {
            throw new AuthenticationServiceException("Please wait for admin approval");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong credentials");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
