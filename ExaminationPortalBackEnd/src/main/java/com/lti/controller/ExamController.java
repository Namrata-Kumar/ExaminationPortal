package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.QuestionDto;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;
import com.lti.service.ExamService;

@RestController
@CrossOrigin
public class ExamController {

	@Autowired
	ExamService examService;

	public boolean isValidAdmin() {

		return false;
	}

	public boolean isValidUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@PostMapping(value = "/register")
	public UserRegistration registerUser(@RequestBody UserRegistration user) {
		return examService.registerUser(user);
	}

	@PostMapping(value = "/updatePassword")
	public long updatePassword(@RequestBody long userId, String userPassword) {
		examService.updatePassword(userId, userPassword);
		return userId;

	}

	@GetMapping(value = "/fetchExamQuestions/{currentLevel}/{courseId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<QuestionDto> fetchExamQuestions(@PathVariable("currentLevel") int currentLevel,@PathVariable("courseId") long courseId) {
		List<Question> questions = examService.fetchExamQuestions(currentLevel,courseId);
		List<QuestionDto> dtoList = new ArrayList<QuestionDto>();
		for (Question question : questions) {
			QuestionDto dto = new QuestionDto();
			dto.setQuestionId(question.getQuestionId());
			dto.setDescription(question.getDescription());
			dto.setAnswer(question.getAnswer());
			dto.setOptionOne(question.getOptionOne());
			dto.setOptionTwo(question.getOptionTwo());
			dto.setOptionThree(question.getOptionThree());
			dto.setOptionFour(question.getOptionFour());
			dto.setCourseId(question.getCourse().getCourseId());
			dto.setExamLevel(question.getExamLevel());
			dtoList.add(dto);
		}
		for (QuestionDto que : dtoList) {
			System.out.println(que.getQuestionId());
		}
		return dtoList;
	}

	public long updateReportCard() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int displayScoreByLevelandId(int complexityLevel, long userId, long courseId) {
		// TODO Auto-generated method stub
		return 0;
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

	@RequestMapping(value = "/viewAllReportCards", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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

	@GetMapping(value = "/findReport/{userId}/{courseId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ReportCard findReportBasedOnCourseAndUserId(@PathVariable("userId") long userId,
			@PathVariable("courseId") long courseId) {
		ReportCard reportCard = examService.findReportBasedOnCourseAndUserId(userId, courseId);
		return reportCard;

	}

}
