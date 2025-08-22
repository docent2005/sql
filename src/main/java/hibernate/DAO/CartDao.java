package hibernate.DAO;

import hibernate.config.HibernateUtil;
import hibernate.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.List;


public class CartDao extends Dao{
    private static final Logger log = LogManager.getLogger(CartDao.class);

    public CartDao() {
        super(Cart.class);
    }

    public List<Cart> getByUser(int id){
        log.info("getByUser called");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Cart c where c.userId = :id", Cart.class).setParameter("id", id).list();
        }catch (Exception e){
            log.error("User_id is not in the DB ",e);
        }
        return null;
    }
}
