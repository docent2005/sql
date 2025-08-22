package jdbc.DAO;

import hibernate.config.HibernateUtil;
import jdbc.entity.Orders;
import jdbc.entity.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class ProductsDaoTest {
    Products product;
    @BeforeEach
    void beforeAll() throws SQLException {
        product = new Products();
        product.setId(1);
        product.setName("test");
        product.setPrice(10);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE products RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
        }
    }
    @Test
    void createAndRead() throws SQLException {
        ProductsDao.create(product);
        Products product2 = ProductsDao.read(1);
        assertNotNull(product2);
        assertEquals(product.getId(), product2.getId(), "Product id is incorrect");
        assertEquals(product.getName(), product2.getName(), "Product name is incorrect");
        assertEquals(product.getPrice(), product2.getPrice(), "Product price is incorrect");
    }

    @Test
    void readAll() throws SQLException {
        ProductsDao.create(product);
        product.setName("test2");
        ProductsDao.create(product);
        ProductsDao.read();
    }

    @Test
    void update() throws SQLException {
        ProductsDao.create(product);
        product.setName("test2");
        ProductsDao.update(product);
        assertEquals("test2", ProductsDao.read(1).getName(), "Product don't update");
    }

    @Test
    void delete() throws SQLException {
        ProductsDao.create(product);
        ProductsDao.delete(product.getId());
        assertNull(ProductsDao.read(1));
    }
}