package hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "details")
    private int details;

    public Users(int id, String name, int details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }
    public Users() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDetails() {
        return details;
    }
    public void setDetails(int details) {
        this.details = details;
    }
}
