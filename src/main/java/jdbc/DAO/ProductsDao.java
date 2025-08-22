package jdbc.DAO;

import jdbc.entity.Products;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsDao extends Dao{
    private static final Logger log = LogManager.getLogger(ProductsDao.class);

    public static void create(Products product) throws SQLException {
        log.info("Create " + product);
        String sql = "insert into products (name, prise) values(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getPrice());
        stmt.executeUpdate();
        if(stmt.getGeneratedKeys().next()){
            product.setId(stmt.getGeneratedKeys().getInt(1));
        }
    }
    public static void read() throws SQLException {
        log.info("Read all products");
        String sql = "select * from products";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: "+ rs.getString("id") +
                    "\tname: " + rs.getString("name") +
                    "\t\t\tprise: " + rs.getString("prise"));
        }

    }
    public static Products read(int id) throws SQLException {
        log.info("Read product " + id);
        String sql = "select * from products where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
       return rs.next() ? new Products(id, rs.getString("name"), rs.getInt("prise")) : null;
    }
    public static void update(Products product) throws SQLException {
        log.info("Update product " + product);
        String sql = "update products set name = ?, prise = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getPrice());
        stmt.setInt(3, product.getId());
        stmt.executeUpdate();
    }
    public static void delete(int id) throws SQLException {
        log.info("Delete product " + id);
        String sql = "delete from products where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
