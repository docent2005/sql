package main.jdbc.DAO;

import main.jdbc.eternity.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDetailsDao extends Dao {
    public static void create(UserDetails details) throws SQLException {
        String sql = "insert into user_details (tell, emile) values(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, details.getTell());
        stmt.setString(2, details.getEmile());
        if(stmt.getGeneratedKeys().next()){
            details.setId(stmt.getGeneratedKeys().getInt(1));
        }
    }
    public static void read() throws SQLException {
        String sql = "select * from user_details";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: "+ rs.getString("id") +
                    "\tTell: " + rs.getString("tell") +
                    "\t\t\tEmile: " + rs.getString("emile"));
        }
    }
    public static void read(int id) throws SQLException {
        String sql = "select * from user_details where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("ID: "+ rs.getString("id") +
                    "\tTell: " + rs.getString("tell") +
                    "\t\t\tEmile: " + rs.getString("emile"));
        }
    }
    public static void update(UserDetails details) throws SQLException {
        String sql = "update user_details set tell = ?, emile = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, details.getTell());
        stmt.setString(2, details.getEmile());
        stmt.setInt(3, details.getId());
    }
    public static void delete(int id) throws SQLException {
        String sql = "delete from user_details where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
    }
}
