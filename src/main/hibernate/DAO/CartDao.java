package main.hibernate.DAO;

import main.hibernate.config.HibernateUtil;
import main.hibernate.eternity.Cart;
import org.hibernate.Session;

import java.util.List;

public class CartDao extends Dao{

    public CartDao() {
        super(Cart.class);
    }
    public List<Cart> getByUser(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Cart c where c.userId = :id", Cart.class).setParameter("id", id).list();
        }
    }
}
