package jdbc;
import jdbc.service.OrderService;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            OrderService.setOrder(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
