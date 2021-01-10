package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;
import com.lti.service.ExamService;

@RestController
public class ExamController {

	@Autowired
	ExamService examService;
    
	@GetMapping("/isValidAdmin/{adminId}/{adminPass}")
	public boolean isValidAdmin(@PathVariable("adminId") long adminId,@PathVariable("adminPass") String adminPassword) {

		return examService.isValidAdmin(adminId, adminPassword);
	}
	@GetMapping("/isValidUser/{userId}/{userPass}")
	public boolean isValidUser(@PathVariable("userId") long userId,@PathVariable("userPass") String userPassword) {
		
		return examService.isValidUser(userId, userPassword);
	}

	@PostMapping(value="/register")
	public UserRegistration registerUser(@RequestBody UserRegistration user) {
		return examService.registerUser(user);
	}

	public void updatePassword() {
		// TODO Auto-generated method stub

	}

	public List<Question> fetchExamQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public long updateReportCard() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int displayScoreBycurrentLevelandId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long addQuestion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long removeQuestion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ReportCard> viewAllReportCards() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserRegistration> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
