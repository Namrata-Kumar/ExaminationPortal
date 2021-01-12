package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserCredentials;
import com.lti.entity.UserRegistration;
import com.lti.service.ExamService;

@RestController
public class ExamController {

	@Autowired
	ExamService examService;

	@PostMapping("/isValidAdmin")
	public boolean isValidAdmin(@RequestBody UserCredentials userCredentials) {
		return examService.isValidAdmin(userCredentials.getEmail(), userCredentials.getPassword());
	}

	@PostMapping("/isValidUser")
	public boolean isValidUser(@RequestBody UserCredentials userCredentials) {
		return examService.isValidUser(userCredentials.getEmail(), userCredentials.getPassword());
	}

	@PostMapping(value = "/register")
	public UserRegistration registerUser(@RequestBody UserRegistration user) {
		return examService.registerUser(user);
	}

	public void updatePassword() {
		// TODO Auto-generated method stub

	}

	@GetMapping(value = "/fetchExamQuestions")
	public List<Question> fetchExamQuestions() {
		return examService.fetchExamQuestions();
	}

	public long updateReportCard() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@GetMapping(value = "/displayScoreByLevelandId/{examLevel}/{userId}/{courseId}")
	public int displayScoreByLevelandId(@PathVariable("examLevel") int examLevel,@PathVariable("userId") long userId,@PathVariable("courseId") long courseId) {
		return examService.displayScoreByLevelandId(examLevel, userId, courseId) ;
	}

	@PostMapping(value = "/addQuestion")
	public long addQuestion(@RequestBody Question question) {
		return examService.addQuestion(question);
	}

	@PostMapping(value = "/removeQuestion", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public long removeQuestion(@RequestParam("questionId") long questionId) {
		return examService.removeQuestion(questionId);

	}

	@RequestMapping(value = "/viewAllReportCards", method = RequestMethod.GET)
	public List<ReportCard> viewAllReportCards() {
		return examService.viewAllReportCards();
	}

	public List<UserRegistration> findUsersByDetails(@PathVariable long courseId, @PathVariable int currentLevel) {
		return null;
	}

	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public List<UserRegistration> viewAllUsers() {
		return examService.viewAllUsers();
	}

}
