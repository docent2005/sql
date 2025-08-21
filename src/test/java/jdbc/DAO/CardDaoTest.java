package jdbc.DAO;

import jdbc.entity.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CardDaoTest {

    @Test
    void create() {
        Card card = new Card();
        card.setUserId(1);
        card.setNumber(1);
        card.setProductId(1);
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void read() {
    }
}