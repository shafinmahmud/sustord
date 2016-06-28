/*
 */
package shafin.sustord.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.model.Course;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class CourseDao {

    @SuppressWarnings("unchecked")
	public static Course getCourseObject(int courseId) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            String hql = "from Course where courseId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", courseId);

            List<Course> course = (List<Course>) query.list();

            session.getTransaction().commit();

            if (!course.isEmpty()) {
                return course.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException(e.getMessage());
        } finally {
            session.close();
        }
    }
}
