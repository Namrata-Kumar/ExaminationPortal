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
	public boolean isValidAdmin(long adminId,String adminPassword) {
		
		return examRepository.isValidAdmin(adminId, adminPassword);
	}

	@Override
	public boolean isValidUser(long userId,String userPassword) {
		
		return examRepository.isValidUser(userId, userPassword);
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
		
		return null;
	}

	@Override
	public long updateReportCard() {
		
		return 0;
	}

	@Override
	public int displayScoreBycurrentLevelandId() {
		
		return 0;
	}

	@Override
	public long addQuestion() {
		
		return 0;
	}

	@Override
	public long removeQuestion() {
		
		return 0;
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
