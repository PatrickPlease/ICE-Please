import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Wardrobe wardrobe = new Wardrobe();
        MainMenu mm = new MainMenu();
        User loggedInUser = mm.login();
        wardrobe.setCurrentUser(loggedInUser);

        Connection connection = null;
        wardrobe.addClothingToWardrobe(connection);

    }
}