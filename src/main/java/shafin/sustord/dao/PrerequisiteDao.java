/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.Prerequisite;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class PrerequisiteDao implements BasicCRUD<Prerequisite> {

	@Override
	public Prerequisite findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Prerequisite where prerequisiteId = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);

			@SuppressWarnings("unchecked")
			List<Prerequisite> pre = (List<Prerequisite>) query.list();
			session.getTransaction().commit();

			return pre.isEmpty() ? null : pre.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Prerequisite> findBySyllabusIdFk(int syllabusId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Prerequisite where syllabusIdFk = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", syllabusId);

			@SuppressWarnings("unchecked")
			List<Prerequisite> pres = (List<Prerequisite>) query.list();
			session.getTransaction().commit();

			return pres;

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Collection<Prerequisite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(Prerequisite object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(Prerequisite object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(Prerequisite object) {
		// TODO Auto-generated method stub
		return false;
	}
}
