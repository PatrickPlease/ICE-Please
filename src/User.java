import java.util.ArrayList;
import java.util.List;

public class User {

    private static int nextId = 1; // Used to assign unique IDs to users
    private int id;
    private String username;
    private String password;
    static ArrayList<User> users = new ArrayList<>();
    private List<Clothing> clothingItems;


    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        users.add(this);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if(DataValidator.isUsernameValid(username)){
            this.username = username;
        }
        else{
            throw new IllegalArgumentException("Username is not valid");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if(DataValidator.isPasswordValid(password)){
            this.password = password;
        }
        else{
            throw new IllegalArgumentException("password is not valid");
        }
    }
    public static ArrayList<User> getUsers() {
        return users;
    }
    public List<Clothing> getClothingItems(){return clothingItems;}

    public static void addUser(User user) {
        users.add(user);
    }

    public String toString() {
        return username + "," + password;
    }
}
