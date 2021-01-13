package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "table_userlogindetails")
public class UserLoginDetails {
   
	@Id
	@SequenceGenerator(name = "seq_userlogin", initialValue = 001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_userlogin")
	long userLoginId;
	String userEmail;
	String forgotPasswordLink;
	
	@OneToOne
	@JoinColumn(name = "userId")
	UserRegistration userRegistration;

	public long getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(long userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getForgotPasswordLink() {
		return forgotPasswordLink;
	}

	public void setForgotPasswordLink(String forgotPasswordLink) {
		this.forgotPasswordLink = forgotPasswordLink;
	}

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	
	
}
