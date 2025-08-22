package hibernate.DAO;


import hibernate.config.HibernateUtil;
import hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DaoTest{
    Cart cart;
    Dao<Cart> dao = new Dao<>(Cart.class);

    @BeforeEach
    public void setCard(){
        cart = new Cart();
        cart.setUserId(1);
        cart.setProductId(1);
        cart.setNumber(1);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE cart RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
    }

    @Test
    public void testCreate() {
        dao.create(cart);
        assertTrue(cart.getId() > 0, "В об'єкт не повернулось id");
        dao.delete(cart);
    }

    @Test
    public void testUpdate() {
        dao.create(cart);
        cart.setNumber(2);
        dao.update(cart);
        assertEquals(2, dao.read(cart.getId()).getNumber(), "Значення Number не змінилось");
        dao.delete(cart);
    }

    @Test
    public void testDelete() {
        dao.create(cart);
        dao.delete(cart);
        assertNull(dao.read(cart.getId()), "В бд залишився рядок з таким індексом");
    }

    @Test
    public void testRead() {
        dao.create(cart);
        Cart cart2 = dao.read(cart.getId());
        assertEquals(cart2.getId(), cart.getId(), "Значення Id не зчиталось");
        assertEquals(cart2.getNumber(), cart.getNumber(), "Значення Number не зчиталось");
        assertEquals(cart2.getUserId(), cart.getUserId(), "Значення UserId не зчиталось");
        assertEquals(cart2.getProductId(), cart.getProductId(), "Значення ProductId не зчиталось");
    }
}