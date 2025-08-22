package hibernate.DAO;

import hibernate.config.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Dao<T> {
    private static final Logger log = LogManager.getLogger(Dao.class);

    private Class<T> clazz;
    public Dao(Class<T> clazz) {
        this.clazz = clazz;
    }
    public void create(T t) {
        log.info("Create " + clazz.getSimpleName() + " " + t);
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(t);
            tx.commit();
        }catch (Exception e) {
            log.error(e);
        }
    }
    public void update(T t) {
        log.info("Update " + clazz.getSimpleName() + " " + t);
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(t);
            tx.commit();
        }
    }
    public void delete(T t) {
        log.info("Delete " + clazz.getSimpleName() + " " + t);
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(t);
            tx.commit();
        }
    }
    public T read(int id) {
        log.info("Read " + clazz.getSimpleName() + " " + id);
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }
}
