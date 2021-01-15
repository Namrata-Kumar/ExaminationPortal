
package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import com.lti.dto.NewReport;
import com.lti.dto.QuestionDto;
import com.lti.dto.ReportCardDto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lti.entity.AdminLoginDetails;
import com.lti.entity.Course;
import com.lti.entity.ForgotPassword;
import com.lti.dto.QuestionDto;
import com.lti.entity.Question;
import com.lti.entity.ReportCard;
import com.lti.entity.ResetPassword;
import com.lti.entity.UserLoginDetails;
import com.lti.entity.UserRegistration;
import com.lti.service.EmailService;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	EntityManager em;

	@Autowired
	EmailService emailService;

	@Transactional
	public boolean isValidAdmin(String email, String password) {
		try {
			String sql = "select a from AdminLoginDetails a where a.adminEmail=:adminEmail";
			System.out.println("after sql");
			TypedQuery<AdminLoginDetails> query = em.createQuery(sql, AdminLoginDetails.class);
			query.setParameter("adminEmail", email);
			System.out.println("after q");
			AdminLoginDetails existingUser = query.getSingleResult();
			System.out.println("after result" + existingUser.getAdminPassword());

			return existingUser.getAdminPassword().equals(password);

		} catch (NoResultException noResultException) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public long isValidUser(String email, String password) {
		try {
			String sql = "select u from UserRegistration u where u.userEmail=:userEmail and u.userPassword=:userPassword";
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			query.setParameter("userEmail", email);
			query.setParameter("userPassword", password);
			UserRegistration existingUser = query.getSingleResult();
			return existingUser.getUserId();
			// return existingUser.getUserPassword().equals(password);
		} catch (Exception e) {
			return 0;
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
	public boolean resetPassword(ResetPassword resetPassword) {
		try {
			String sql = "select u from UserRegistration u where u.userEmail=:userEmail";
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			query.setParameter("userEmail", resetPassword.getUserEmail());
			UserRegistration existingUser = query.getSingleResult();
			if ((existingUser.getUserEmail().equals(resetPassword.getUserEmail()))
					&& (resetPassword.getCode().equals(existingUser.getForgotPasswordLink()))) {
				String jpql = "UPDATE UserRegistration u SET u.userPassword=:userPassword WHERE u.userEmail=:userEmail";
				Query query1 = em.createQuery(jpql);
				query1.setParameter("userPassword", resetPassword.getConfirmPassword());
				query1.setParameter("userEmail", resetPassword.getUserEmail());
				query1.executeUpdate();
				return true;
			} else {
				return false;
			}
		} catch (NoResultException noResultException) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean forgotPassword(ForgotPassword forgotPassword) {
		try {
			String sql = "select u from UserRegistration u where u.userEmail=:userEmail";
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			query.setParameter("userEmail", forgotPassword.getUserEmail());
			UserRegistration existingUser = query.getSingleResult();
			if (existingUser.getUserEmail().equals(forgotPassword.getUserEmail())) {
				String code = this.generateRandomString();
				String jpql = "UPDATE UserRegistration u SET u.forgotPasswordLink=:code WHERE u.userEmail=:userEmail";
				Query query1 = em.createQuery(jpql);
				query1.setParameter("code", code);
				query1.setParameter("userEmail", forgotPassword.getUserEmail());
				query1.executeUpdate();
				// send email here
				UserRegistration user = this.findUserByEmail(forgotPassword.getUserEmail());
				String subject = "Reset Password Link";
				String email = user.getUserEmail();
				String text = "Hi " + user.getUserName() + "!! Your Password reset link is:"
						+ "http://localhost:4200/resetpassword/" + code;
				emailService.sendEmailForPasswordReset(email, text, subject);
				return true;
			} else {
				return false;
			}
		} catch (NoResultException noResultException) {
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	private String generateRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 20;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	@Transactional
	public UserRegistration findUserByEmail(String userEmail) {
		String sql = "SELECT u from UserRegistration u where u.userEmail=:userEmail";
		try {
			TypedQuery<UserRegistration> query = em.createQuery(sql, UserRegistration.class);
			query.setParameter("userEmail", userEmail);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Transactional
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
	public long addQuestion(QuestionDto question) {
		// System.out.println(question.get);
		Question question1 = new Question();
		Course course = em.find(Course.class, question.getCourseId());
		question1.setCourse(course);
		question1.setDescription(question.getDescription());
		question1.setExamLevel(question.getExamLevel());
		question1.setOptionOne(question.getOptionOne());
		question1.setOptionTwo(question.getOptionTwo());
		question1.setOptionThree(question.getOptionThree());
		question1.setOptionFour(question.getOptionFour());
		question1.setAnswer(question.getAnswer());
		Question question2 = em.merge(question1);
		return question2.getQuestionId();

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

	@Override
	public long updatePassword(long userId, String userPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	public long updateExistingReport(ReportCardDto reportCard) {
		ReportCard report = new ReportCard();
		Course course = em.find(Course.class, reportCard.getCourseId());
		UserRegistration user = em.find(UserRegistration.class, reportCard.getUserId());
		report.setCourse(course);
		report.setUserRegistration(user);
		report.setCurrentLevel(reportCard.getCurrentLevel());
		report.setLevel1Score(reportCard.getLevel1Score());
		report.setLevel2Score(reportCard.getLevel2Score());
		report.setLevel3Score(reportCard.getLevel3Score());
		report.setReportId(reportCard.getReportId());
		report.setStatus(reportCard.getStatus());
		ReportCard updatedReport = em.merge(report);
		return updatedReport.getReportId();
	}

}
