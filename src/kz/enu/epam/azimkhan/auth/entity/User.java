package kz.enu.epam.azimkhan.auth.entity;

/**
 * User
 */
public class User implements Entity {

    /**
     * user id
     */
    private int id;

    /**
     * username
     */
    private String username;

    /**
     * hashed password
     */
    private String password;

    /**
     * Default constructor
     */
    public User(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!password.equals(user.password)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "User#"+id+"[username="+username+"]";
    }
}
