package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.entity.Course;
import com.lti.entity.ForgotPassword;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.ResetPassword;
import com.lti.entity.UserRegistration;
import com.lti.repository.ExamRepository;
import com.lti.dto.NewReport;
import com.lti.dto.QuestionDto;
import com.lti.dto.ReportCardDto;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	EmailService emailService;

	@Override
	public boolean isValidAdmin(String email, String password) {

		return examRepository.isValidAdmin(email, password);
	}

	@Override
	public long isValidUser(String email, String password) {

		return examRepository.isValidUser(email, password);
	}

	@Override
	public UserRegistration registerUser(UserRegistration user) {
		return examRepository.registerUser(user);
	}


	@Override
	public List<Question> fetchExamQuestions(int currentLevel, long courseId) {
		return examRepository.fetchExamQuestions(currentLevel, courseId);
	}

	@Override
	public long updateReportCard() {

		return 0;
	}

	@Override
	public int displayScoreByLevelandId(int examLevel, long userId, long courseId) {

		return examRepository.displayScoreByLevelandId(examLevel, userId, courseId);
	}

	@Override
	public long addQuestion(QuestionDto question) {

		return examRepository.addQuestion(question);

	}

	@Override
	public long addReport(ReportCard reportCard) {
		return examRepository.addReport(reportCard);
	}

	@Override
	public long removeQuestion(long questionId) {

		return examRepository.removeQuestion(questionId);
	}

	@Override
	public List<ReportCard> viewAllReportCards() {
		return examRepository.viewAllReportCards();
	}

	@Override
	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel) {

		return null;
	}

	@Override
	public List<UserRegistration> viewAllUsers() {

		return examRepository.viewAllUsers();
	}

	public ReportCard findReportBasedOnCourseAndUserId(long userId, long courseId) {
		return examRepository.findReportBasedOnCourseAndUserId(userId, courseId);

	}

	public List<Course> fetchAllCourses() {
		return examRepository.fetchAllCourses();
	}

	public long addNewReport(NewReport newReport) {
		return examRepository.addNewReport(newReport);

	}

	@Override
	public boolean resetPassword(ResetPassword resetPassword) {
		return examRepository.resetPassword(resetPassword);

	}
	
	@Override
	public boolean forgotPassword(ForgotPassword forgotPassword) {
		return examRepository.forgotPassword(forgotPassword);
	}

	@Override
	public UserRegistration findUserByEmail(String userEmail) {
		return examRepository.findUserByEmail(userEmail);
	}

	public long  updateExistingReport(ReportCardDto reportCard) {
		return examRepository.updateExistingReport(reportCard);
	}

}
