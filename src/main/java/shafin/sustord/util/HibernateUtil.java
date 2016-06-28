/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.util;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author SHAFIN
 */
public class HibernateUtil {

    private static SessionFactory sessionFactorySingletone;

    public static SessionFactory getSessionFactory() throws HibernateException, SQLException {
        if (sessionFactorySingletone == null) {

            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactorySingletone = configuration.buildSessionFactory(builder.build());

        }
        return sessionFactorySingletone;
    }
}
