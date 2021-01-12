package com.lti.repository;

import java.util.ArrayList;
import java.util.List;
import com.lti.dto.QuestionDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.dto.QuestionDto;
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
		return 0;
	}

	@Transactional
	public List<Question> fetchExamQuestions() {
		QuestionDto que = new QuestionDto();
		List<QuestionDto> questionList = new ArrayList<QuestionDto>();
		String sql = "select q from Question q";
		try {
			TypedQuery<Question> query = em.createQuery(sql, Question.class);
			List<Question> questions = query.getResultList();
			return questions;

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

	public ReportCard findReportBasedOnCourseAndUserId(long userId, long courseId) {
		String sql = "select r from ReportCard r where r.userRegistration.userId=:userId and r.course.courseId=:courseId";
		try {
			TypedQuery<ReportCard> query = em.createQuery(sql, ReportCard.class);
			query.setParameter("userId", userId);
			query.setParameter("courseId", courseId);
			//System.out.println("Hello");
			ReportCard reportCard = query.getSingleResult();
			System.out.println(reportCard);
			
			return reportCard;

		} catch (Exception e) {
			System.out.println("hello"+e.getMessage());
			
			return null;
		}

	}

}
