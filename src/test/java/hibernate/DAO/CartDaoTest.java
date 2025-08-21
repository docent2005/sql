package hibernate.DAO;



import hibernate.entity.Cart;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDaoTest extends DaoTest{

    @Test
    void getByUser() {
        Cart cart1 = new Cart();
        cart1.setUserId(1);
        cart1.setNumber(2);
        cart1.setProductId(2);
        Cart cart2 = new Cart();
        cart2.setUserId(1);
        cart2.setNumber(3);
        cart2.setProductId(3);
        dao.create(cart1);
        dao.create(cart2);
        dao.create(cart);
        CartDao cartDao = new CartDao();
        List<Cart> cartList = cartDao.getByUser(cart.getUserId());
        assertEquals(cartList.size(), 3, "Кількість не збігається");
        for (Cart cart : cartList) {
            assertEquals(1, cart.getUserId(), "ID юзера не збігається");
        }
        dao.delete(cart);
        dao.delete(cart2);
        dao.delete(cart1);
    }
}