 package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.AdminLoginDetails;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean isValidAdmin(long adminId,String adminPassword) {
		try {
			AdminLoginDetails admin = em.find(AdminLoginDetails.class,adminId);
			if (admin.getAdminPassword().equals(adminPassword)) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean isValidUser(long userId,String userPassword) {
		try {
			UserRegistration user = em.find(UserRegistration.class,userId);
			if (user.getUserPassword().equals(userPassword)) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public UserRegistration registerUser(UserRegistration user) {
		UserRegistration newUser= em.merge(user);
		//return newUser.getUserId();
		return newUser;
	}

	@Transactional
	public void updatePassword() {
		

	}

	@Transactional
	public List<Question> fetchExamQuestions() {
		
		return null;
	}

	@Transactional
	public long updateReportCard() {
		
		return 0;
	}

	@Transactional
	public int displayScoreBycurrentLevelandId() {
		
		return 0;
	}

	@Transactional
	public long addQuestion() {
		
		return 0;
	}

	@Transactional
	public long removeQuestion() {
		
		return 0;
	}

	@Transactional
	public List<ReportCard> viewAllReportCards() {
		
		return null;
	}

	@Transactional
	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel) {
		
		return null;
	}

	@Transactional
	public List<UserRegistration> viewAllUsers() {
		
		return null;
	}


}
