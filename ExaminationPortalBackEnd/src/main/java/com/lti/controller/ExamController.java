
package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

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

import com.lti.dto.ForgotPassword;
import com.lti.dto.NewReport;
import com.lti.dto.QuestionDto;
import com.lti.dto.ReportCardDto;
import com.lti.dto.ResetPassword;
import com.lti.dto.UserByDetails;
import com.lti.entity.Course;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;
import com.lti.service.ExamService;

@RestController
@CrossOrigin
public class ExamController {

	@Autowired
	ExamService examService;

	@GetMapping("/isValidUser/{uemail}/{upass}")
	public long isValidUser(@PathVariable("uemail") String email, @PathVariable("upass") String password) {
		System.out.println(email + password);
		return examService.isValidUser(email, password);
	}

	@GetMapping("/isValidAdmin/{aemail}/{apass}")
	public boolean isValidAdmin(@PathVariable("aemail") String email, @PathVariable("apass") String password) {
		return examService.isValidAdmin(email, password);
	}

	@PostMapping(value = "/register")
	public UserRegistration registerUser(@RequestBody UserRegistration user) {
		return examService.registerUser(user);
	}

	@PostMapping(value = "/resetPassword")
	public boolean resetPassword(@RequestBody ResetPassword resetPassword) {
		return examService.resetPassword(resetPassword);
	}

	@PostMapping(value = "/forgotPassword")
	public boolean forgotPassword(@RequestBody ForgotPassword forgotPassword) {
		return examService.forgotPassword(forgotPassword);
	}

	@GetMapping(value = "/fetchExamQuestions/{currentLevel}/{courseId}")
	public List<QuestionDto> fetchExamQuestions(@PathVariable("currentLevel") int currentLevel,
			@PathVariable("courseId") long courseId) {
		List<Question> questions = examService.fetchExamQuestions(currentLevel, courseId);
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

	@GetMapping(value = "/displayScoreByLevelandId/{examLevel}/{userId}/{courseId}")
	public int displayScoreByLevelandId(@PathVariable("examLevel") int examLevel, @PathVariable("userId") long userId,
			@PathVariable("courseId") long courseId) {
		return examService.displayScoreByLevelandId(examLevel, userId, courseId);
	}

	@PostMapping(value = "/addQuestion")
	public long addQuestion(@RequestBody QuestionDto question) {
		return examService.addQuestion(question);
	}

	@GetMapping(value = "/removeQuestion")
	public long removeQuestion(@RequestParam("questionId") long questionId) {
		return examService.removeQuestion(questionId);
	}

	@RequestMapping(value = "/viewAllReportCards")
	public List<ReportCard> viewAllReportCards() {
		return examService.viewAllReportCards();
	}

	@GetMapping(value = "/findUsersByDetails")
	public List<UserByDetails> findUsersByDetails(@RequestParam("courseId") long courseId,
			@RequestParam("userState") String userState, @RequestParam("userCity") String userCity,
			@RequestParam("level") int level, @RequestParam("fromRange") int fromRange,
			@RequestParam("toRange") int toRange) {
		return examService.findUsersByDetails(courseId, userState, userCity, level, fromRange, toRange);
	}

	/*
	 * @GetMapping(value = "/findUsersByDetailsNew") public List<UserRegistration>
	 * findUsersByDetailNew(
	 * 
	 * @RequestParam("userState") String userState, @RequestParam("userCity") String
	 * userCity,
	 * 
	 * @RequestParam("fromRange") int fromRange, @RequestParam("toRange") int
	 * toRange) { return examService.findUsersByDetailsNew(userState, userCity,
	 * fromRange, toRange); }
	 */

	@RequestMapping(value = "/viewAllUsers", method = RequestMethod.GET)
	public List<UserRegistration> viewAllUsers() {
		return examService.viewAllUsers();
	}

	@GetMapping(value = "/findReport/{userId}/{courseId}")
	public ReportCard findReportBasedOnCourseAndUserId(@PathVariable("userId") long userId,
			@PathVariable("courseId") long courseId) {
		ReportCard reportCard = examService.findReportBasedOnCourseAndUserId(userId, courseId);
		return reportCard;

	}

	@GetMapping(value = "/fetchAllCourses")
	public List<Course> fetchAllCourses() {
		return examService.fetchAllCourses();
	}

	@PostMapping(value = "/addNewReport")
	public long addNewReport(@RequestBody NewReport newReport) {
		return examService.addNewReport(newReport);
	}

	@PostMapping(value = "/updateReport")
	public long updateExistingReport(@RequestBody ReportCardDto reportCard) {
		return examService.updateExistingReport(reportCard);

	}

	@GetMapping(value = "reportByUserId/{userId}")
	public List<ReportCardDto> viewReportsByUserId(@PathVariable("userId") long userId) {
		System.out.println("in controller");
		return examService.viewReportsByUserId(userId);
	}

}
