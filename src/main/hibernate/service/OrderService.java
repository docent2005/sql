package main.hibernate.service;

import main.hibernate.DAO.CartDao;
import main.hibernate.DAO.Dao;
import main.hibernate.eternity.Cart;
import main.hibernate.eternity.Orders;
import main.hibernate.eternity.Products;

import java.util.List;

public class OrderService {
    public static void setOrder(int id){
        CartDao cartDao = new CartDao();
        Dao<Products> productDao = new Dao(Products.class);
        List<Cart> carts = cartDao.getByUser(id);
        int sum = 0;
        StringBuilder bld = new StringBuilder();
        for (Cart cart : carts) {
            bld.append(productDao.read(cart.getProductId()).getName() +
                    " - " + cart.getNumber()+ "шт. ");
            sum+=cart.getNumber()*productDao.read(cart.getProductId()).getPrice();
        }
        Dao<Orders> ordersDao = new Dao(Orders.class);
        Orders orders = new Orders();
        orders.setUserId(id);
        orders.setTotalSum(sum);
        orders.setProductsList(bld.toString());
        ordersDao.create(orders);
    }
}
