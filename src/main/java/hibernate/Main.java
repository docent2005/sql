package hibernate;

import hibernate.DAO.CartDao;
import hibernate.DAO.Dao;

import hibernate.entity.Cart;
import hibernate.entity.Orders;
import hibernate.entity.Products;
import jdbc.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CartDao cartDao = new CartDao();
        Dao<Products> dao = new Dao(Products.class);
        List<Cart> carts = cartDao.getByUser(1);
        for (Cart cart : carts) {
            System.out.println(dao.read(cart.getProductId()).getName());
        }
        Dao<Orders> ordersDao = new Dao(Orders.class);


        try {
            OrderService.setOrder(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
