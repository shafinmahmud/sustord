/*
 */
package shafin.sustord.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import shafin.sustord.model.Department;
import shafin.sustord.util.HibernateUtil;

/**
 *
 * @author SHAFIN
 */
public class DepartmentDao {
    
    @SuppressWarnings("unchecked")
	public static Department getDepartmentObject(int departmentId) throws Exception {
        Department department;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        try {
            String hql = "from Department where departmentId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", departmentId);

            List<Department> depts = (List<Department>) query.list();

            session.getTransaction().commit();

            if (!depts.isEmpty()) {
                department = depts.get(0);
            }else{
                department = null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException(e.getMessage());
        } finally{
            session.close();
        }

        return department;
    }
}
