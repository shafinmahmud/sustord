/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.AdminInfo;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class AdminInfoDao implements BasicCRUD<AdminInfo> {

	@Override
	public AdminInfo findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from AdminInfo where adminInfoId = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<AdminInfo> infos = (List<AdminInfo>) query.list();
			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}
	

	public AdminInfo findOneByAdminNo(String adminNo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from AdminInfo where adminNo = :adminNo";
			Query query = session.createQuery(hql);
			query.setParameter("adminNo", adminNo);

			@SuppressWarnings("unchecked")
			List<AdminInfo> infos = (List<AdminInfo>) query.list();
			session.getTransaction().commit();

			return infos.isEmpty() ? null : infos.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public Collection<AdminInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(AdminInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(AdminInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(AdminInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

}
