package jdbc.entity;

public class Card {
    private int userId;
    private int productId;
    private int number;

    public Card(int userId, int productId, int number) {
        this.userId = userId;
        this.productId = productId;
        this.number = number;
    }
    public Card(){}
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
