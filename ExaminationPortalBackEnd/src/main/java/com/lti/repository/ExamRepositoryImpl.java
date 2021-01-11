package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserRegistration;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean isValidAdmin() {

		return false;
	}

	@Transactional
	public boolean isValidUser() {

		return false;
	}

	@Transactional
	public UserRegistration registerUser(UserRegistration user) {
		UserRegistration newUser = em.merge(user);
		// return newUser.getUserId();
		return newUser;
	}

	@Transactional
	public long updatePassword(long userId, String userPassword) {
//		UPDATE QuestionGroupText T SET T.localizedText.content = :content WHERE T.generatedId = :generatedId
		String jpql = "UPDATE UserRegistration T SET T.userPassword = :userPassword WHERE T.userId = :userId ";
		Query query = em.createQuery(jpql);
//		UserRegistration newuser = query.getSingleResult();
		return userId;

	}

	@Transactional
	public List<Question> fetchExamQuestions() {
		String sql = "select q from Question q";
		try {
			TypedQuery<Question> query = em.createQuery(sql, Question.class);
			List<Question> question = query.getResultList();
			return question;
		} catch (Exception e) {
			return null;
		}

	}

	@Transactional
	public long updateReportCard() {

		return 0;
	}

	@Transactional
	public int displayScoreByLevelandId(int complexityLevel, long userId, long courseId) {
		return 0;
	}

	@Transactional
	public long addQuestion(Question question) {
		Question question1 = em.merge(question);
		return question1.getQuestionId();

	}

	@Transactional
	public long removeQuestion(long questionId) {
		Question question = em.find(Question.class, questionId);
		em.remove(question);
		return question.getQuestionId();
	}

	@Transactional
	public List<ReportCard> viewAllReportCards() {
		String sql = "select r from ReportCard r";
		try {
			TypedQuery<ReportCard> query = em.createQuery(sql, ReportCard.class);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<UserRegistration> findUsersByDetails(long courseId, int currentLevel) {
		
			return null;
		
	}

	@Transactional
	public List<UserRegistration> viewAllUsers() {
		String sql = "select u from UserRegistration u";
		try {
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
