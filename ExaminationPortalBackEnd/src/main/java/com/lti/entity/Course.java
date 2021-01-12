package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "table_courses")
public class Course {
   
	@Id
	@SequenceGenerator(name = "seq_course", initialValue = 5001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course")
	long courseId;
	String courseName;
	
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonIgnore
	List<ReportCard> reportCard;
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonIgnore
	List<Question> question;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	

	public List<ReportCard> getReportCard() {
		return reportCard;
	}

	public void setReportCard(List<ReportCard> reportCard) {
		this.reportCard = reportCard;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	
	
}