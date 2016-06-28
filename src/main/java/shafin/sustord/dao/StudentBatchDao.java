package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.StudentBatch;
import shafin.sustord.util.HibernateUtil;

public class StudentBatchDao implements BasicCRUD<StudentBatch>{

	@Override
	public StudentBatch findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from StudentBatch where studentBatchId = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<StudentBatch> batch = (List<StudentBatch>) query.list();
			session.getTransaction().commit();

			return batch.isEmpty() ? null : batch.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}
	
	public StudentBatch findOneByBatchTag(String tag) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from StudentBatch where batchTag = :tag";
			Query query = session.createQuery(hql);
			query.setParameter("tag", tag);

			@SuppressWarnings("unchecked")
			List<StudentBatch> batch = (List<StudentBatch>) query.list();
			session.getTransaction().commit();

			return batch.isEmpty() ? null : batch.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public Collection<StudentBatch> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(StudentBatch object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(StudentBatch object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(StudentBatch object) {
		// TODO Auto-generated method stub
		return false;
	}

}
