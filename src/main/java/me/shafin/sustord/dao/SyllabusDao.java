/*
 */
package me.shafin.sustord.dao;

import java.util.List;
import me.shafin.sustord.model.Syllabus;
import me.shafin.sustord.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SHAFIN
 */
public class SyllabusDao {

    @SuppressWarnings("unchecked")
	public static List<Syllabus> getSyllabusObjectsOfSemester(int studentBatchIdFk, int semester) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            String hql = "from Syllabus where studentBatchIdFk = :batchId and semester = :sem";
            Query query = session.createQuery(hql);
            query.setInteger("batchId", studentBatchIdFk);
            query.setInteger("sem", semester);

            List<Syllabus> syllabusList = (List<Syllabus>) query.list();

            session.getTransaction().commit();

            if (!syllabusList.isEmpty()) {
                return syllabusList;
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
