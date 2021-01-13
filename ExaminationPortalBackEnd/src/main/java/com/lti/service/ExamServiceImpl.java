package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;
import com.lti.repository.ExamRepository;
import com.lti.dto.QuestionDto;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamRepository examRepository;

	@Override
	public boolean isValidAdmin(String email,String password) {
		
		return examRepository.isValidAdmin(email, password);
	}

	@Override
	public boolean isValidUser(String email, String password){

		return examRepository.isValidUser(email, password);
	}

	@Override
	public UserRegistration registerUser(UserRegistration user) {
		return examRepository.registerUser(user);
	}

	public long updatePassword(long userId, String userPassword) {
		return examRepository.updatePassword(userId, userPassword);

	}

	@Override
	public List<Question> fetchExamQuestions(int currentLevel,long courseId) {
		return examRepository.fetchExamQuestions(currentLevel,courseId);
	}

	@Override
	public long updateReportCard() {

		return 0;
	}

	@Override
	public int displayScoreByLevelandId(int examLevel,long userId,long courseId) {

		return examRepository.displayScoreByLevelandId(examLevel, userId, courseId) ;
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
		return examRepository.viewAllReportCards();
	}

	
	  @Override public List<UserRegistration> findUsersByDetails(long courseId, int
	  currentLevel) {
	  
	  return null;
	  }
	 

	@Override
	public List<UserRegistration> viewAllUsers() {
		
		return examRepository.viewAllUsers();
	}
	public ReportCard findReportBasedOnCourseAndUserId(long userId,long courseId) {
		return examRepository.findReportBasedOnCourseAndUserId(userId,courseId);

	}


}
