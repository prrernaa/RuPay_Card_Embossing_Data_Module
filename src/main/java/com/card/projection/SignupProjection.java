package com.card.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignupProjection {
	@JsonProperty("username")
	private String username;
    
	
	@JsonProperty("email")
    private String email;
    
	
	@JsonProperty("userRole")
    private String userRole;
    
	
	@JsonProperty("mobileNum")
    private String mobileNum;

    public SignupProjection(String username, String email, String userRole, String mobileNum) {
        this.username = username;
        this.email = email;
        this.userRole = userRole;
        this.mobileNum = mobileNum;
    }

    // Getter and setter methods for the properties
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
}
