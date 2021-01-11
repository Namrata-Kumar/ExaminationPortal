package com.lti.service;

import java.util.List;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;

public interface ExamService {

	public boolean isValidAdmin();

	public boolean isValidUser();

	public UserRegistration registerUser(UserRegistration user);

	public long updatePassword(long userId, String userPassword);

	public List<Question> fetchExamQuestions();

	public long updateReportCard();

	public int displayScoreByLevelandId(int complexityLevel,long userId,long courseId);

	public long addQuestion(Question question);

	public long removeQuestion(long questionId);

	public List<ReportCard> viewAllReportCards();

	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel);

	public List<UserRegistration> viewAllUsers();

}
