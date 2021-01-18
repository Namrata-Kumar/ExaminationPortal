package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "table_report")
public class ReportCard {

	@Id
	@SequenceGenerator(name = "seq_report", initialValue = 4001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report")
	long reportId;

	int level1Score;

	int level2Score;

	int level3Score;
	int currentLevel;

	int status;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "courseId")
	Course course;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	UserRegistration userRegistration;

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
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

	public int getLevel1Score() {
		return level1Score;
	}

	public void setLevel1Score(int level1Score) {
		this.level1Score = level1Score;
	}

	public int getLevel2Score() {
		return level2Score;
	}

	public void setLevel2Score(int level2Score) {
		this.level2Score = level2Score;
	}

	public int getLevel3Score() {
		return level3Score;
	}

	public void setLevel3Score(int level3Score) {
		this.level3Score = level3Score;
	}

}