package jdbc.entity;

public class Orders {
    private int id;
    private int userId;
    private String productsList;
    private int totalSum;

    public Orders(int id, int userId, String productsList, int totalSum) {
        this.id = id;
        this.userId = userId;
        this.productsList = productsList;
        this.totalSum = totalSum;
    }
    public Orders() {}

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public String getProductsList() {
        return productsList;
    }
    public int getTotalSum() {
        return totalSum;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }
    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

}
