package jdbc;
import jdbc.DAO.CardDao;
import jdbc.entity.Card;
import jdbc.service.OrderService;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Card card;
            card = new Card();
            card.setUserId(1);
            card.setNumber(1);
            card.setProductId(1);
            CardDao.create(card);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
