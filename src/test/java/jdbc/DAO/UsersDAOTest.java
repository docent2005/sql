package jdbc.DAO;

import hibernate.config.HibernateUtil;
import jdbc.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class UsersDAOTest {

    Users user ;
    @BeforeEach
    void beforeAll() throws SQLException {
        user = new Users();
        user.setId(1);
        user.setName("test");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
        UsersDAO.create(user);
    }
    @Test
    void createAndRead() throws SQLException {
        Users user1 = UsersDAO.read(1);
        assertNotNull(user1);
        assertEquals(user1.getId(), user.getId(), "id mismatch");
        assertEquals(user1.getName(), user.getName(), "name mismatch");
    }

    @Test
    void readAll() throws SQLException {
        user.setName("test123");
        UsersDAO.create(user);
        UsersDAO.read();
    }

    @Test
    void update() throws SQLException {
       user.setName("test123");
       UsersDAO.update(user);
       assertEquals(user.getName(), UsersDAO.read(1).getName(), "name mismatch");
    }

    @Test
    void delete() throws SQLException {
        UsersDAO.delete(1);
        assertNull(UsersDAO.read(1));
    }
}