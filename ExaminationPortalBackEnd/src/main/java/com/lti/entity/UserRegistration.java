package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "table_userRegistration")
public class UserRegistration {
    
	
	@Id
	@SequenceGenerator(name = "seq_user", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	long userId;
	String userName;
	String userEmail;
	String userPassword;
	String userPhone;
	LocalDate dateOfBirth;
	String userCity;
	String userState;
	String qualification;
	String yearOfCompletion;
	String forgotPasswordLink;
	 
	@OneToOne(mappedBy = "userRegistration",cascade = CascadeType.ALL)
	UserLoginDetails userLoginDetails;
	
	@OneToMany(mappedBy = "userRegistration")
	List<ReportCard> reportCard;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getYearOfCompletion() {
		return yearOfCompletion;
	}

	public void setYearOfCompletion(String yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}

	public UserLoginDetails getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(UserLoginDetails userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	public List<ReportCard> getReportCard() {
		return reportCard;
	}

	public void setReportCard(List<ReportCard> reportCard) {
		this.reportCard = reportCard;
	}

	public String getForgotPasswordLink() {
		return forgotPasswordLink;
	}

	public void setForgotPasswordLink(String forgotPasswordLink) {
		this.forgotPasswordLink = forgotPasswordLink;
	}
	
	
	
}


// "userName":"meghana",
// "userEmail":"meg@lti",
// "userPassword":"sdfg",
// "userPhone":"9876543",
// "dateOfBirth":"12-12-2020",
// "userCity":"hyd",
// "userState":"tel",
// "qualification":"btech",
// "yearOfCompletion":"2020"
