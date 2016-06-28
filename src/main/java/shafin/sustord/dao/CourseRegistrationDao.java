/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.CourseRegistration;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class CourseRegistrationDao implements BasicCRUD<CourseRegistration> {

	@Override
	public CourseRegistration findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from CourseRegistration where courseRegistrationId = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);

			@SuppressWarnings("unchecked")
			List<CourseRegistration> reg = (List<CourseRegistration>) query.list();
			session.getTransaction().commit();

			return reg.isEmpty() ? null : reg.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Collection<CourseRegistration> findByStudentInfoIdFkAndAttendSemester(int studentInfoId, int semester) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from CourseRegistration where studentInfoIdFk = :id and attendSemester = :sem";
			Query query = session.createQuery(hql);
			query.setInteger("id", studentInfoId);
			query.setInteger("sem", semester);

			@SuppressWarnings("unchecked")
			List<CourseRegistration> regs = (List<CourseRegistration>) query.list();
			session.getTransaction().commit();

			return regs;

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Collection<CourseRegistration> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(CourseRegistration object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(CourseRegistration object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(CourseRegistration object) {
		// TODO Auto-generated method stub
		return false;
	}
}
