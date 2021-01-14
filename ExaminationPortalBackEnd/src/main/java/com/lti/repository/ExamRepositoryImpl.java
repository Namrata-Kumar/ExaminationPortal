package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import com.lti.dto.NewReport;
import com.lti.dto.QuestionDto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.lti.entity.AdminLoginDetails;
import com.lti.entity.Course;
import com.lti.dto.QuestionDto;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.UserLoginDetails;
import com.lti.entity.UserRegistration;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean isValidAdmin(String email, String password) {
		try {
			String sql = "select a from AdminLoginDetails a where a.adminEmail=:adminEmail";
			TypedQuery<AdminLoginDetails> query = em.createQuery(sql, AdminLoginDetails.class);
			query.setParameter("adminEmail", email);
			AdminLoginDetails existingUser = query.getSingleResult();
			return existingUser.getAdminPassword().equals(password);

		} catch (NoResultException noResultException) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean isValidUser(String email, String password) {
		try {
			String sql = "select u from UserRegistration u where u.userEmail=:userEmail";
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			query.setParameter("userEmail", email);
			UserRegistration existingUser = query.getSingleResult();
			return existingUser.getUserPassword().equals(password);
		} catch (NoResultException noResultException) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public UserRegistration registerUser(UserRegistration user) {
		String sql = "select u from UserRegistration u where u.userEmail=:userEmail";
		TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
		query.setParameter("userEmail", user.getUserEmail());
		List<UserRegistration> existingUserRegistrations = query.getResultList();
		if (existingUserRegistrations.isEmpty()) {
			UserRegistration newUser = em.merge(user);
			return newUser;
		}
		return null;
		// return newUser.getUserId();
	}

	@Transactional
	public long updatePassword(long userId, String userPassword) {
		return 0;
	}

	public List<Question> fetchExamQuestions(int examLevel, long courseId) {
		String sql = "select q from Question q where q.examLevel=:examLevel and q.course.courseId=:courseId";
		try {
			TypedQuery<Question> query = em.createQuery(sql, Question.class);
			query.setParameter("examLevel", examLevel);
			query.setParameter("courseId", courseId);
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

	/*
	 * @Transactional public int displayScoreByLevelandId(int examLevel, long
	 * userId, long courseId) { String sql =
	 * "select r from ReportCard r where r.UserRegistration.userId=:userId and r.Course.courseId=:courseId"
	 * ; TypedQuery<ReportCard> query = em.createQuery(sql, ReportCard.class);
	 * query.setParameter("userId", userId); query.setParameter("courseId",
	 * courseId); ReportCard reportCard = null; try { reportCard =
	 * query.getSingleResult();
	 * 
	 * } catch (NoResultException noResultException) { return 0; } catch (Exception
	 * e) { return 0; } if (examLevel == 1) { return reportCard.getLevel1Score(); }
	 * else if (examLevel == 2) { return reportCard.getLevel2Score(); } else if
	 * (examLevel == 3) { return reportCard.getLevel3Score(); } return 0; }
	 */

	@Transactional
	public long addQuestion(Question question) {
		Question question1 = em.merge(question);
		return question1.getQuestionId();

	}

	@Transactional
	public long addReport(ReportCard reportCard) {
		ReportCard reportCard1 = em.merge(reportCard);
		return reportCard1.getReportId();
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
	public List<Course> fetchAllCourses() {
		String sql = "select c from Course c";
		try {
			TypedQuery<Course> query = em.createQuery(sql, Course.class);
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
			// System.out.println("Hello");
			ReportCard reportCard = query.getSingleResult();
			System.out.println(reportCard);

			return reportCard;

		} catch (Exception e) {
			System.out.println("hello" + e.getMessage());

			return null;
		}

	}

	@Override
	public int displayScoreByLevelandId(int examLevel, long userId, long courseId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	public long addNewReport(NewReport newReport) {
		ReportCard reportCard = new ReportCard();
		Course course = em.find(Course.class, newReport.getCourseId());
		UserRegistration userRegistration = em.find(UserRegistration.class, newReport.getUserId());
		reportCard.setUserRegistration(userRegistration);
		reportCard.setCourse(course);
		reportCard.setCurrentLevel(1);
		reportCard.setLevel1Score(0);
		reportCard.setLevel2Score(0);
		reportCard.setLevel3Score(0);
		reportCard.setStatus(0);

		/*
		 * String sql = "insert into ReportCard values(?,?,?,?,?,?,?);"; Query query =
		 * em.createQuery(sql, ReportCard.class); query.setParameter(1, 1);
		 * query.setParameter(2, 0); query.setParameter(3, 0); query.setParameter(4, 0);
		 * query.setParameter(5, 0); query.setParameter(6, newReport.getCourseId());
		 * query.setParameter(7, newReport.getUserId()); ReportCard reportCard =
		 * (ReportCard) query.getSingleResult(); return reportCard.getReportId();
		 */
		ReportCard reportCard1 = em.merge(reportCard);
		return reportCard1.getReportId();
	}

}
