import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Wardrobe wardrobe = new Wardrobe();
        MainMenu mainMenu = new MainMenu();
        User loggedInUser = mainMenu.login();


        if (loggedInUser != null) {
            System.out.println("User logged in: " + loggedInUser.getUsername());

            wardrobe.setCurrentUser(loggedInUser);
            Connection connection = null;
            wardrobe.addClothingToWardrobe(connection);
        } else {
            System.out.println("User not logged in.");
        }
    }
}