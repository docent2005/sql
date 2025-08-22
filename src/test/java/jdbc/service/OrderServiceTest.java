package jdbc.service;

import hibernate.config.HibernateUtil;
import jdbc.DAO.*;
import jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE user_details RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE users RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE products RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE orders RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE cart RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        UsersDAO.create(new Users(1,"test", 1));
        UsersDAO.create(new Users(2,"test2", 1));
        UserDetailsDao.create(new UserDetails(1, "test", "test"));
        ProductsDao.create(new Products(1, "test", 1));
        CardDao.create(new Card(1, 1,1));
    }
    @Test
    void setOrder() throws SQLException {
        OrderService.setOrder(1);
        Orders order = OrdersDao.read(1).getFirst();
        assertEquals(1, order.getUserId(), "Не вірний id користувача");
        assertEquals(1, order.getTotalSum(), "Не вірно розраховано суму");
        assertEquals("test - 1шт. ", order.getProductsList(), "Не вірний список покупок");
    }
}