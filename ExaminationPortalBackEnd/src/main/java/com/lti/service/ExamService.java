package com.lti.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.lti.dto.NewReport;
import com.lti.dto.QuestionDto;
import com.lti.dto.ReportCardDto;
import com.lti.entity.Course;
import com.lti.entity.ForgotPassword;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.ResetPassword;
import com.lti.entity.UserRegistration;

public interface ExamService {

	public boolean isValidAdmin(String email, String password);

	public long isValidUser(String email, String password);

	public UserRegistration registerUser(UserRegistration user);

	public List<Question> fetchExamQuestions(int currentLevel, long courseId);

	public long updateReportCard();

	public int displayScoreByLevelandId(int examLevel, long userId, long courseId);

	public long addQuestion(QuestionDto question);

	public long removeQuestion(long questionId);

	public List<ReportCard> viewAllReportCards();

	public List<UserRegistration> findUsersByDetails(long courseId,String userState, String userCity, int level,
			int fromRange, int toRange);
	
	//public List<UserRegistration> findUsersByDetailsNew(String userState, String userCity, int fromRange, int toRange);

	public List<UserRegistration> viewAllUsers();

	public ReportCard findReportBasedOnCourseAndUserId(long userId, long courseId);

	public long addReport(ReportCard reportCard);

	public List<Course> fetchAllCourses();

	public long addNewReport(NewReport newReport);

	public UserRegistration findUserByEmail(String userEmail);

	public boolean resetPassword(ResetPassword resetPassword);

	public boolean forgotPassword(ForgotPassword forgotPassword);

	public long updateExistingReport(ReportCardDto reportCard);
}
