/*
 */
package me.shafin.sustord.dao;

import java.sql.SQLException;
import java.util.List;
import me.shafin.sustord.model.CourseRegistration;
import me.shafin.sustord.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SHAFIN
 */
public class CourseRegistrationDao {

    @SuppressWarnings("unchecked")
	public static CourseRegistration getCourseRegistrationObject(int courseRegistrationId) throws HibernateException, SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            String hql = "from CourseRegistration where courseRegistrationId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", courseRegistrationId);

            List<CourseRegistration> courseRegistrations = (List<CourseRegistration>) query.list();

            session.getTransaction().commit();

            if (!courseRegistrations.isEmpty()) {
                return courseRegistrations.get(0);
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
    
    @SuppressWarnings("unchecked")
	public static List<CourseRegistration> getRegisteredCourseListOfSemester(Integer studentInfoId, int semester) throws HibernateException, SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            String hql = "from CourseRegistration where studentInfoIdFk = :id and attendSemester = :sem";
            Query query = session.createQuery(hql);
            query.setInteger("id", studentInfoId);
            query.setInteger("sem", semester);

            List<CourseRegistration> courseRegistrations = (List<CourseRegistration>) query.list();

            session.getTransaction().commit();

            if (!courseRegistrations.isEmpty()) {
                return courseRegistrations;
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
