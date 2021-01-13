package com.lti.service;

import java.util.List;

import com.lti.dto.QuestionDto;
import com.lti.entity.Course;
import com.lti.entity.ForgotPassword;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.ResetPassword;
import com.lti.entity.UserRegistration;

public interface ExamService {

	public boolean isValidAdmin(String email, String password);

	public boolean isValidUser(String email, String password);

	public UserRegistration registerUser(UserRegistration user);

	public boolean resetPassword(ResetPassword resetPassword);
	
	public boolean forgotPassword(ForgotPassword forgotPassword);

	public List<Question> fetchExamQuestions(int currentLevel,long courseId);

	public long updateReportCard();

	public int displayScoreByLevelandId(int examLevel,long userId,long courseId);

	public long addQuestion(Question question);

	public long removeQuestion(long questionId);

	public List<ReportCard> viewAllReportCards();

	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel);

	public List<UserRegistration> viewAllUsers();
	
	public ReportCard findReportBasedOnCourseAndUserId(long userId,long courseId);
	
	public long addReport(ReportCard reportCard);
	
	public List<Course> fetchAllCourses();
	
    public UserRegistration findUserByEmail(String userEmail);
	
}
