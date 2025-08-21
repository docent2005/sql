package jdbc.service;

import jdbc.DAO.CardDao;
import jdbc.DAO.OrdersDao;
import jdbc.DAO.ProductsDao;
import jdbc.entity.Card;
import jdbc.entity.Orders;
import jdbc.entity.Products;
import jdbc.DAO.*;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public static void setOrder(int id) throws SQLException {
        List<Card> list = CardDao.read(id);
        StringBuilder bld = new StringBuilder();
        int sum = 0;
        for (Card card : list) {
            Products product = ProductsDao.read(card.getProductId());
            bld.append(product.getName()).append(" - " + card.getNumber() + "шт. ");
            sum += product.getPrice()*card.getNumber();
        }
        Orders order = new Orders(1, id, bld.toString(), sum);
//        System.out.println(bld.toString());
//        System.out.println(sum);
        OrdersDao.create(order);
//        System.out.println(order.getId());
    }
}
