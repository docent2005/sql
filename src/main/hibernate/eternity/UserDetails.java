package main.hibernate.eternity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tell")
    private String tell;
    @Column(name = "emile")
    private String emile;
    public UserDetails() {}
    public UserDetails(int id, String tell, String emile) {
        this.id = id;
        this.tell = tell;
        this.emile = emile;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTell() {
        return tell;
    }
    public void setTell(String tell) {
        this.tell = tell;
    }
    public String getEmile() {
        return emile;
    }
    public void setEmile(String emile) {
        this.emile = emile;
    }
}
