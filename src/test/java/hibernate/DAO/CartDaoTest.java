package hibernate.DAO;



import hibernate.config.HibernateUtil;
import hibernate.entity.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDaoTest extends DaoTest{

    @BeforeEach
    public void clearDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE cart RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
    }
    @Test
    void getByUser() {

        Cart cart1 = new Cart();
        cart1.setUserId(1);
        cart1.setNumber(2);
        cart1.setProductId(1);
        Cart cart2 = new Cart();
        cart2.setUserId(1);
        cart2.setNumber(3);
        cart2.setProductId(1);
        dao.create(cart);
        dao.create(cart1);
        dao.create(cart2);

        CartDao cartDao = new CartDao();
        List<Cart> cartList = cartDao.getByUser(cart.getUserId());
        assertEquals(cartList.size(), 3, "Кількість не збігається");
    }
}