package hibernate.service;

import hibernate.DAO.Dao;
import hibernate.config.HibernateUtil;
import hibernate.entity.Cart;
import hibernate.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OrderServiceTest {

    @Test
    void setOrder() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE orders RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        Cart cart = new Cart();
        cart.setProductId(1);
        cart.setUserId(8);
        cart.setNumber(1);
        Dao<Cart> cartDao = new Dao<>(Cart.class);
        cartDao.create(cart);
        OrderService.setOrder(8);
        Dao<Orders> dao = new Dao<>(Orders.class);
        assertEquals(8, dao.read(1).getUserId(), "Не вірний id користувача");
        assertEquals(50, dao.read(1).getTotalSum(), "Не вірно розраховано суму");
        assertEquals("гречка - 1шт. ", dao.read(1).getProductsList(), "Не вірно розраховано суму");
        cartDao.delete(cart);
    }
}