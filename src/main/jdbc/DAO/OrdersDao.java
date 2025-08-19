package main.jdbc.DAO;

import main.jdbc.eternity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao extends Dao{
    public static void create(Orders o) throws SQLException {
        String query = "insert into orders (user_id, products_list, total_sum) values(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, o.getUserId());
        stmt.setString(2, o.getProductsList());
        stmt.setDouble(3, o.getTotalSum());
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            o.setId(rs.getInt(1));
        }
    }
    public static List<Orders> read(int id) throws SQLException {
        String query = "select * from orders where user_id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<Orders> orders = new ArrayList<Orders>();
        while (rs.next()) {
            orders.add(new Orders(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("products_list"),
                    rs.getInt("total_sum")));
        }
        return orders;
    }
    public static List<Orders> read() throws SQLException {
        String query = "select * from orders";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Orders> orders = new ArrayList<Orders>();
        while (rs.next()) {
            orders.add(new Orders(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("products_list"),
                    rs.getInt("total_sum")));
        }
        return orders;
    }
}
