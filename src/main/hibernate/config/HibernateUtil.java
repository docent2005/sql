package main.hibernate.config;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import main.hibernate.eternity.*;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try{
            Properties settings = new Properties();
            settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            settings.put("hibernate.connection.username", "postgres");
            settings.put("hibernate.connection.password", "admin");
            settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            settings.put("hibernate.hbm2ddl.auto", "update");

            Configuration configuration = new Configuration();
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(Users.class);
            configuration.addAnnotatedClass(UserDetails.class);
            configuration.addAnnotatedClass(Products.class);
            configuration.addAnnotatedClass(Orders.class);
            configuration.addAnnotatedClass(Cart.class);

            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Помилка конфігурації Hibernate", e);
        }

    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
