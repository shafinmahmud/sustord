/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.Department;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class DepartmentDao implements BasicCRUD<Department> {

	@Override
	public Department findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			String hql = "from Department where departmentId = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);

			@SuppressWarnings("unchecked")
			List<Department> depts = (List<Department>) query.list();
			
			session.getTransaction().commit();

			return depts.isEmpty() ? null : depts.get(0);

		} catch (HibernateException | SQLException e) {
			throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Collection<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(Department object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(Department object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(Department object) {
		// TODO Auto-generated method stub
		return false;
	}
}
