package com.lti.dto;

import org.hibernate.annotations.ColumnDefault;

public class ReportCardDto {
	long reportId;
	int level1Score;
	int level2Score;
	int level3Score;
	int currentLevel;
	int status;
	long courseId;
	long userId;

	public long getReportId() {
		return reportId;
	}

	public void setReportId(long reportId) {
		this.reportId = reportId;
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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
