package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;
import com.lti.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamRepository examRepository;

	@Override
	public boolean isValidAdmin() {

		return false;
	}

	@Override
	public boolean isValidUser() {

		return false;
	}

	@Override
	public UserRegistration registerUser(UserRegistration user) {
		return examRepository.registerUser(user);
	}

	@Override
	public void updatePassword() {

	}

	@Override
	public List<Question> fetchExamQuestions() {
		return examRepository.fetchExamQuestions();
	}

	@Override
	public long updateReportCard() {

		return 0;
	}

	@Override
	public int displayScoreByLevelandId(int complexityLevel,long userId,long courseId) {

		return 0;
	}

	@Override
	public long addQuestion(Question question) {

		return examRepository.addQuestion(question);

	}

	@Override
	public long removeQuestion(long questionId) {

		return examRepository.removeQuestion(questionId);
	}

	@Override
	public List<ReportCard> viewAllReportCards() {

		return null;
	}

	@Override
	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel) {

		return null;
	}

	@Override
	public List<UserRegistration> viewAllUsers() {

		return null;
	}

}
