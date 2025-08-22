package jdbc.DAO;


import hibernate.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jdbc.entity.Orders;
import jdbc.entity.UserDetails;
import jdbc.entity.Users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class OrdersDaoTest {
    Orders order;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE user_details RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        UsersDAO.create(new Users(1,"test", 1));
        UsersDAO.create(new Users(2,"test2", 1));
        UserDetailsDao.create(new UserDetails(1, "test", "test"));
    }
    @BeforeEach
    void beforeAll() throws SQLException {
        order = new Orders(1, 1, "test", 1);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE orders RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
    }
    @Test
    void createAndRead() throws SQLException {
        OrdersDao.create(order);
        Orders newOrder = OrdersDao.read(1).getFirst();
        assertNotNull(newOrder);
        assertEquals(order.getProductsList(), newOrder.getProductsList(), "Product list doesn't match");
        assertEquals(order.getTotalSum(), newOrder.getTotalSum(), "Total sum doesn't match");
        assertEquals(order.getUserId(), newOrder.getUserId(), "User id doesn't match");
    }

    @Test
    void readAll() throws SQLException {
        OrdersDao.create(order);
        order.setUserId(2);
        OrdersDao.create(order);
        Orders firstOrder = OrdersDao.read().getFirst();
        Orders lastOrder = OrdersDao.read().getLast();
        assertNotNull(firstOrder, "First order doesn't exist");
        assertNotNull(lastOrder, "Last order doesn't exist");
        assertNotEquals(firstOrder.getUserId(), lastOrder.getUserId(), "First order doesn't match");

    }
}