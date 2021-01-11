package com.lti.repository;

import java.util.List;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;

public interface ExamRepository {

	public boolean isValidAdmin();

	public boolean isValidUser();

	public UserRegistration registerUser(UserRegistration user);

	public void updatePassword();

	public List<Question> fetchExamQuestions();

	public long updateReportCard();

	public int displayScoreBycurrentLevelandId();

	public long addQuestion(Question question);

	public long removeQuestion(long questionId);

	public List<ReportCard> viewAllReportCards();

	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel);

	public List<UserRegistration> viewAllUsers();

}
