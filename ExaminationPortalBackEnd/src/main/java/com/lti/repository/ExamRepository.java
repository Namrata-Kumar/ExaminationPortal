package com.lti.repository;

import java.util.List;

import com.lti.dto.QuestionDto;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;

public interface ExamRepository {

	public boolean isValidAdmin(String email, String password);

	public boolean isValidUser(String email, String password);

	public UserRegistration registerUser(UserRegistration user);

	public long updatePassword(long userId, String userPassword);

	public List<Question> fetchExamQuestions();

	public long updateReportCard();

	public int displayScoreByLevelandId(int examLevel,long userId,long courseId);

	public long addQuestion(Question question);

	public long removeQuestion(long questionId);

	public List<ReportCard> viewAllReportCards();

	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel);

	public List<UserRegistration> viewAllUsers();
	public ReportCard findReportBasedOnCourseAndUserId(long userId,long courseId);

}
