/*
 */
package shafin.sustord.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.entity.Course;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class CourseDao implements BasicCRUD<Course>{

	@Override
	public Course findOne(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			 String hql = "from Course where courseId = :id";
	            Query query = session.createQuery(hql);
	            query.setInteger("id", id);

	            @SuppressWarnings("unchecked")
				List<Course> course = (List<Course>) query.list();
			session.getTransaction().commit();

			return course.isEmpty() ? null : course.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}
	
	public Course findOneByCourseCode(int code) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			 String hql = "from Course where courseCode = :code";
	            Query query = session.createQuery(hql);
	            query.setInteger("courseCode", code);

	            @SuppressWarnings("unchecked")
				List<Course> course = (List<Course>) query.list();
			session.getTransaction().commit();

			return course.isEmpty() ? null : course.get(0);
			
		} catch (HibernateException | SQLException e) {
				throw new ExceptionInInitializerError(e.getMessage());
		} finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public Collection<Course> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertOne(Course object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOne(Course object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOne(Course object) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
