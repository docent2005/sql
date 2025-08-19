package main.jdbc.service;


import main.hibernate.eternity.Cart;
import main.hibernate.eternity.Orders;
import main.hibernate.eternity.Products;
import main.hibernate.DAO.*;


import java.sql.SQLException;
import java.util.List;

public class OrderService {
    public static void setOrder(int id) throws SQLException {
        CartDao cartDao = new CartDao();
        Dao<Products> dao = new Dao(Products.class);
        List<Cart> list = cartDao.getByUser(id);
        StringBuilder bld = new StringBuilder();
        int sum = 0;
        for (Cart card : list) {
            Products product = dao.read(card.getProductId());
            bld.append(product.getName()).append(" - " + card.getNumber() + "шт. ");
            sum += product.getPrice()*card.getNumber();
        }
        Orders order = new Orders();
        order.setProductsList(bld.toString());
        order.setUserId(id);
        order.setTotalSum(sum);
        Dao<Orders> ordersDao = new Dao(Orders.class);
        ordersDao.create(order);
    }
}
