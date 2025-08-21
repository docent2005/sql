package jdbc.DAO;

import jdbc.db.DataBase;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao {
    static Connection conn;
    static {
        try {
            conn = DataBase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
