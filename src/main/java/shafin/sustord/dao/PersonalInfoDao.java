
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.PersonalInfo;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class PersonalInfoDao implements BasicCRUD<PersonalInfo> {

	@Override
	public PersonalInfo findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			String hql = "from PersonalInfo where personalInfoId = :id and isApproved = 1";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);

			@SuppressWarnings("unchecked")
			List<PersonalInfo> infos = (List<PersonalInfo>) query.list();

			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	public PersonalInfo findOneUnapproevd(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			String hql = "from PersonalInfo where personalInfoId = :id and isApproved = 0";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);

			@SuppressWarnings("unchecked")
			List<PersonalInfo> infos = (List<PersonalInfo>) query.list();

			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}
	

	@Override
	public Collection<PersonalInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(PersonalInfo personalInfo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(personalInfo);
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
	public boolean updateOne(PersonalInfo personalInfo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(personalInfo);
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
	public boolean deleteOne(PersonalInfo personalInfo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(personalInfo);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}
}
