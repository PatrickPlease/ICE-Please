import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Wardrobe wardrobe = new Wardrobe();
        User currentUser = CreateAccount.createUser();
        wardrobe.setCurrentUser(currentUser);

        Connection connection = null;
        wardrobe.createOutfit();

    }
}