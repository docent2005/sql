package jdbc.DAO;

import hibernate.DAO.CartDao;
import jdbc.entity.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends Dao{
    private static final Logger log = LogManager.getLogger(CartDao.class);

    public static void create(Card card) throws SQLException {
        log.info("Create " + card);
        String sql = "insert into cart (user_id, products_id, number) values(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, card.getUserId());
        stmt.setInt(2, card.getProductId());
        stmt.setInt(3, card.getNumber());
    }
    public static void delete(Card card) throws SQLException {
        log.info("Delete " + card);
        String sql = "delete from cart where user_id=? and product_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, card.getUserId());
        stmt.setInt(2, card.getProductId());
    }
    public static void delete(int id) throws SQLException {
        log.info("Delete " + id);
        String sql = "delete from cart where user_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
    }
    public static List<Card> read(int id) throws SQLException {
        log.info("Read " + id);
        String sql = "select * from cart where user_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<Card> cards = new ArrayList<Card>();
        while (rs.next()) {
            cards.add(new Card(rs.getInt("user_id"), rs.getInt("products_id"), rs.getInt("number")));
        }
        return cards;
    }
}
