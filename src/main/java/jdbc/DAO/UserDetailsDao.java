package jdbc.DAO;

import hibernate.DAO.CartDao;
import jdbc.entity.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDetailsDao extends Dao {
    private static final Logger log = LogManager.getLogger(UserDetailsDao.class);

    public static void create(UserDetails details) throws SQLException {
        log.info("Create " + details);
        String sql = "insert into user_details (tell, emile) values(?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, details.getTell());
        stmt.setString(2, details.getEmile());
        stmt.executeUpdate();
        if(stmt.getGeneratedKeys().next()){
            details.setId(stmt.getGeneratedKeys().getInt(1));
        }
    }
    public static void read() throws SQLException {
        log.info("Read all details");
        String sql = "select * from user_details";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: "+ rs.getString("id") +
                    "\tTell: " + rs.getString("tell") +
                    "\t\t\tEmile: " + rs.getString("emile"));
        }
    }
    public static UserDetails read(int id) throws SQLException {
        log.info("Read " + id);
        String sql = "select * from user_details where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? new UserDetails(rs.getInt("id"), rs.getString("tell"), rs.getString("emile")): null;
    }
    public static void update(UserDetails details) throws SQLException {
        log.info("Update " + details);
        String sql = "update user_details set tell = ?, emile = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, details.getTell());
        stmt.setString(2, details.getEmile());
        stmt.setInt(3, details.getId());
        stmt.executeUpdate();
    }
    public static void delete(int id) throws SQLException {
        log.info("Delete " + id);
        String sql = "delete from user_details where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
