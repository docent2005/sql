package hibernate.service;

import hibernate.DAO.Dao;
import hibernate.config.HibernateUtil;
import hibernate.entity.Cart;
import hibernate.entity.Orders;
import jdbc.DAO.ProductsDao;
import jdbc.DAO.UserDetailsDao;
import jdbc.DAO.UsersDAO;
import jdbc.entity.Products;
import jdbc.entity.UserDetails;
import jdbc.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OrderServiceTest {
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
    }
    @Test
    void setOrder() {
        Cart cart = new Cart();
        cart.setProductId(1);
        cart.setUserId(1);
        cart.setNumber(1);
        Dao<Cart> cartDao = new Dao<>(Cart.class);
        cartDao.create(cart);
        OrderService.setOrder(1);
        Dao<Orders> dao = new Dao<>(Orders.class);
        assertEquals(1, dao.read(1).getUserId(), "Не вірний id користувача");
        assertEquals(1, dao.read(1).getTotalSum(), "Не вірно розраховано суму");
        assertEquals("test - 1шт. ", dao.read(1).getProductsList(), "Не вірний список покупок");
        cartDao.delete(cart);
    }
}