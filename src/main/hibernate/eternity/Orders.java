package main.hibernate.eternity;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "products_list")
    private String productsList;
    @Column(name = "total_sum")
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
