package model.utils;

import model.BookValue;
import model.MatchResult;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(BookValue.class);
            configuration.addAnnotatedClass(MatchResult.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
