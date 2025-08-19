package main.jdbc.DAO;

import main.jdbc.eternity.Card;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends Dao{
    public static void create(Card card) throws SQLException {
        String sql = "insert into cart (user_id, products_id, number) values(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, card.getUserId());
        stmt.setInt(2, card.getProductId());
        stmt.setInt(3, card.getNumber());
    }
    public static void delete(Card card) throws SQLException {
        String sql = "delete from cart where user_id=? and product_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, card.getUserId());
        stmt.setInt(2, card.getProductId());
    }
    public static void delete(int id) throws SQLException {
        String sql = "delete from cart where user_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
    }
    public static List<Card> read(int id) throws SQLException {
        String sql = "select * from cart where user_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<Card> cards = new ArrayList<Card>();
        while (rs.next()) {
            cards.add(new Card(rs.getInt("user_id"), rs.getInt("product_id"), rs.getInt("number")));
        }
        return cards;
    }
}
