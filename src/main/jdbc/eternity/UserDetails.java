package main.jdbc.eternity;

public class UserDetails {
    private int id;
    private String tell;
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
