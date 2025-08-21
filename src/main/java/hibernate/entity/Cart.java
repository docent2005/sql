package hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "products_id", nullable = false)
    private int productId;
    @Column(name = "number", nullable = false)
    private int number;

    public Cart(int userId, int productId, int number) {
        this.userId = userId;
        this.productId = productId;
        this.number = number;
    }

    public Cart() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
