package main.jdbc;
import main.jdbc.DAO.CardDao;
import main.jdbc.DAO.OrdersDao;
import main.jdbc.DAO.UsersDAO;
import main.jdbc.eternity.Orders;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Orders> list = OrdersDao.read();
            for (Orders o : list) {
                System.out.println(o.getProductsList());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
