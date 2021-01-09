package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.lti.dto.LevelScore;

@Entity
@Table(name = "table_report")
public class ReportCard {
   
	@Id
	@SequenceGenerator(name = "seq_report", initialValue = 4001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report")
	long reportId;
	LevelScore marksObtained;
	int currentLevel;
	int status;
	
	@OneToOne
	@JoinColumn(name = "courseId")
	Course course;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	UserRegistration userRegistration;

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
	}

	public LevelScore getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(LevelScore marksObtained) {
		this.marksObtained = marksObtained;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	
	
	
}
