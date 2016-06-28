/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.StudentInfo;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class StudentInfoDao implements BasicCRUD<StudentInfo> {

	@Override
	public StudentInfo findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from StudentInfo where StudentInfoId = :id";
			Query query = session.createQuery(hql);
			query.setParameter("reg", id);

			@SuppressWarnings("unchecked")
			List<StudentInfo> infos = (List<StudentInfo>) query.list();
			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public StudentInfo findOneByRegistrationNo(String registrationNo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from StudentInfo where registrationNo = :reg";
			Query query = session.createQuery(hql);
			query.setParameter("reg", registrationNo);

			@SuppressWarnings("unchecked")
			List<StudentInfo> infos = (List<StudentInfo>) query.list();
			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<StudentInfo> findByBatchId(int batchId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from StudentInfo where studentBatchIdFk = :batchId";
			Query query = session.createQuery(hql);
			query.setInteger("batchId", batchId);

			@SuppressWarnings("unchecked")
			List<StudentInfo> infos = (List<StudentInfo>) query.list();
			session.getTransaction().commit();

			return infos;

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Collection<StudentInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(StudentInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(StudentInfo studentInfo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(studentInfo);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public boolean deleteOne(StudentInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

}
