import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int user_id;

    static ArrayList<User> users = new ArrayList<>();
    private List<Clothing> clothingItems;


    public User(String username, String password, String email) {
        setUsername(username);
        setPassword(password);
        this.email = email;
        users.add(this);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getUser_id(){return user_id;
    }
    public void setUser_id(int user_id){
        this.user_id=user_id;
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

    public String getEmail() {
        return email;
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
