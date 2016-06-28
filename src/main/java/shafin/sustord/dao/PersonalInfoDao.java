
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

			String hql = "from PersonalInfo where personalInfoId = :id";
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
	public boolean insertOne(PersonalInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(PersonalInfo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(PersonalInfo object) {
		// TODO Auto-generated method stub
		return false;
	}
}
