package hibernate.service;

import hibernate.DAO.CartDao;
import hibernate.DAO.Dao;
import hibernate.entity.Cart;
import hibernate.entity.Orders;
import hibernate.entity.Products;

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
