/*
 */
package me.shafin.sustord.dao;

import java.sql.SQLException;
import java.util.List;
import me.shafin.sustord.model.AdminInfo;
import me.shafin.sustord.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SHAFIN
 */
public class AdminInfoDao {
    
    public static AdminInfo getAdminInfoObject(String adminNo) throws HibernateException, SQLException{
        AdminInfo adminInfo = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            String hql = "from AdminInfo where adminNo = :no";
            Query query = session.createQuery(hql);
            query.setParameter("no", adminNo);

            List<AdminInfo> infos = (List<AdminInfo>) query.list();
            session.getTransaction().commit();

            if (!infos.isEmpty()) {
                adminInfo = infos.get(0);
            } 
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e.getMessage());
        } finally {
            session.close();
        }

        return adminInfo;
    }
    
}
