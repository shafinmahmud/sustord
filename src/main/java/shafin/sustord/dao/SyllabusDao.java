/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.Syllabus;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class SyllabusDao implements BasicCRUD<Syllabus> {

	@Override
	public Syllabus findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Syllabus where syllabusId = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<Syllabus> syllabus = (List<Syllabus>) query.list();
			session.getTransaction().commit();

			return syllabus.isEmpty() ? null : syllabus.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Syllabus> findSyllabusByBatchIdandSemester(int batchId, int semester) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Syllabus where studentBatchIdFk = :batchId and semester = :sem";
			Query query = session.createQuery(hql);
			query.setInteger("batchId", batchId);
			query.setInteger("sem", semester);

			@SuppressWarnings("unchecked")
			List<Syllabus> syllabus = (List<Syllabus>) query.list();
			session.getTransaction().commit();

			return syllabus;

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Collection<Syllabus> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(Syllabus object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(Syllabus object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(Syllabus object) {
		// TODO Auto-generated method stub
		return false;
	}

}
