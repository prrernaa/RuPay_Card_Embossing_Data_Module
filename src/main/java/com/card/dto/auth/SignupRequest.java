package com.card.dto.auth;

public class SignupRequest {
	
	private String email;
    private String username;
    private String password;
    private String userRole;
    private String mobileNum;
    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getMobilenum() {
		return mobileNum;
	}

	public void setMobilenum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

}
