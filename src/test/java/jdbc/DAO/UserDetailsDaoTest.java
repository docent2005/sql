package jdbc.DAO;

import hibernate.config.HibernateUtil;
import jdbc.entity.Products;
import jdbc.entity.UserDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class UserDetailsDaoTest {
    UserDetails userDetails ;
    @BeforeEach
    void beforeAll() throws SQLException {
        userDetails = new UserDetails();
        userDetails.setId(1);
        userDetails.setTell("test");
        userDetails.setEmile("test");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE user_details RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        UserDetailsDao.create(userDetails);
    }
    @Test
    void createAndRead() throws SQLException {
        UserDetails userDetails1 = UserDetailsDao.read(1);
        assertNotNull(userDetails1);
        assertEquals(userDetails.getId(), userDetails1.getId(), "id mismatch");
        assertEquals(userDetails.getTell(), userDetails1.getTell(), "tel mismatch");
        assertEquals(userDetails.getEmile(), userDetails1.getEmile(), "emile mismatch");

    }

    @Test
    void readAll() throws SQLException {
        userDetails.setTell("test123");
        userDetails.setEmile("test123");
        UserDetailsDao.create(userDetails);
        UserDetailsDao.read();
    }

    @Test
    void update() throws SQLException {
        userDetails.setTell("test123");
        UserDetailsDao.update(userDetails);
        assertEquals(userDetails.getTell(), UserDetailsDao.read(1).getTell(), "tel mismatch");

    }

    @Test
    void delete() throws SQLException {
        UserDetailsDao.delete(1);
        assertNull(UserDetailsDao.read(1));
    }
}