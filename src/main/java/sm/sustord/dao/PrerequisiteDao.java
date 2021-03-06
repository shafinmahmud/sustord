/*
 */
package sm.sustord.dao;

import java.util.ArrayList;
import java.util.List;

import sm.sustord.model.Prerequisite;
import sm.sustord.utility.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SHAFIN
 */
public class PrerequisiteDao {
    
     @SuppressWarnings("unchecked")
	public static List<Prerequisite> getPrerequisiteObjectsList(int syllabusIdFk) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            String hql = "from Prerequisite where syllabusIdFk = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", syllabusIdFk);

            List<Prerequisite> prerequisiteCourseList = (List<Prerequisite>) query.list();

            session.getTransaction().commit();

            if (!prerequisiteCourseList.isEmpty()) {
                return prerequisiteCourseList;
            } else {
                return new ArrayList<Prerequisite>();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException(e.getMessage());
        } finally {
            session.close();
        }
    }
}
