package jdbc.DAO;

import hibernate.config.HibernateUtil;
import jdbc.entity.Card;
import jdbc.entity.Products;
import jdbc.entity.UserDetails;
import jdbc.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class CardDaoTest {
    Card card;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE user_details RESTART IDENTITY CASCADE").executeUpdate();
            session.createNativeQuery("TRUNCATE TABLE products RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        UsersDAO.create(new Users(1,"test", 1));
        UsersDAO.create(new Users(2,"test2", 1));
        UserDetailsDao.create(new UserDetails(1, "test", "test"));
        ProductsDao.create(new Products(1, "test", 1));
    }
    @BeforeEach
    void beforeAll(){
        card = new Card();
        card.setUserId(1);
        card.setNumber(1);
        card.setProductId(1);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE cart RESTART IDENTITY CASCADE").executeUpdate();

            tx.commit();
        }
    }
    @Test
    void create() throws SQLException {
        CardDao.create(card);
        Card card2 = CardDao.read(1).getFirst();
        assertEquals(card.getNumber(), card2.getNumber(), "Number not match");
        assertEquals(card.getProductId(), card2.getProductId(), "Product id not match");
        assertEquals(card.getUserId(), card2.getUserId(), "User id not match");
    }

    @Test
    void deleteAll() throws SQLException {
        CardDao.create(card);
        Card card2 = CardDao.read(1).getFirst();
        CardDao.create(card2);
        CardDao.delete(1);
        assertEquals(0, CardDao.read(1).size(), "Size must be 0");
    }

    @Test
    void delete() throws SQLException {
        CardDao.create(card);
        CardDao.delete(1);
        assertEquals(0, CardDao.read(1).size(), "Size must be 0");
    }

    @Test
    void read() throws SQLException {
        CardDao.create(card);
        Card card2 = CardDao.read(1).getFirst();
        assertEquals(card.getNumber(), card2.getNumber(), "Number not match");
        assertEquals(card.getProductId(), card2.getProductId(), "Product id not match");
        assertEquals(card.getUserId(), card2.getUserId(), "User id not match");
    }
}