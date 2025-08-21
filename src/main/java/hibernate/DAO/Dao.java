package hibernate.DAO;

import hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Dao<T> {
    private Class<T> clazz;
    public Dao(Class<T> clazz) {
        this.clazz = clazz;
    }
    public void create(T t) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(t);
            tx.commit();
        }
    }
    public void update(T t) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(t);
            tx.commit();
        }
    }
    public void delete(T t) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(t);
            tx.commit();
        }
    }
    public T read(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }
}
