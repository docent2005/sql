package jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static Connection conn;
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static Connection getConnection() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
}

