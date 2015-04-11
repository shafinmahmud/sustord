/*
 */
package me.shafin.sustord.dao;

import java.sql.SQLException;
import java.util.List;
import me.shafin.sustord.model.StudentInfo;
import me.shafin.sustord.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SHAFIN
 */
public class StudentInfoDao {

    public static StudentInfo getStudentInfoObject(String registrationNo) throws HibernateException, SQLException{
        StudentInfo studentInfo;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            String hql = "from StudentInfo where registrationNo = :reg";
            Query query = session.createQuery(hql);
            query.setParameter("reg", registrationNo);

            List<StudentInfo> infos = (List<StudentInfo>) query.list();

            session.getTransaction().commit();

            if (!infos.isEmpty()) {
                studentInfo = infos.get(0);
            } else {
                studentInfo = null;
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e.getMessage());
        } finally {
            session.close();
        }

        return studentInfo;
    }

}
