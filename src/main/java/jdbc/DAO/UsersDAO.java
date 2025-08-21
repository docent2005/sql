package jdbc.DAO;

import jdbc.entity.Users;
import java.sql.*;


public class UsersDAO extends Dao {
    public static void create(Users user) throws SQLException {
        String sql = "insert into users (name, details) values(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getName());
        stmt.setInt(2, user.getDetails());
        if(stmt.getGeneratedKeys().next()){
            user.setId(stmt.getGeneratedKeys().getInt(1));
        }
    }
    public static void read() throws SQLException {
        String sql = "select * from users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: "+ rs.getString("id") +
                            "\tName: " + rs.getString("name") +
                            "\t\t\tDetails ID: " + rs.getString("details"));
        }
    }
    public static Users read(int id) throws SQLException {
        String sql = "select * from users where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? new Users(rs.getInt("id"), rs.getString("name"), rs.getInt("details")) : null;
    }
    public static void update(Users user) throws SQLException {
        String sql = "update users set name = ?, details = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setInt(2, user.getDetails());
        stmt.setInt(3, user.getId());
    }
    public static void delete(int id) throws SQLException {
        String sql = "delete from users where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
    }
}
