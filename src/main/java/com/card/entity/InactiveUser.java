// InactiveUser.java
package com.card.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inactive_user_info")
@AllArgsConstructor
@NoArgsConstructor
public class InactiveUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username")
	private String username;
    
    @Column(name="email")
    private String email;
    
    @Column(name="mobileNum")
    private String mobileNum;
    
    @Column(name="remark")
    private String remark;
    
    
    @Column(name="user_role")
    private String userRole;

    // Constructors, getters, setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public InactiveUser(String username, String email, String mobileNum, String remark , String userRole) {
		super();
		this.username = username;
		this.email = email;
		this.mobileNum = mobileNum;
		this.remark = remark;
		this.userRole = userRole;
	}
	public String getUserrole() {
		return userRole;
	}
	public void setUserrole(String userrole) {
		this.userRole = userrole;
	}
}
