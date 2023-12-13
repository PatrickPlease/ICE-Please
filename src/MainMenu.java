import java.sql.Connection;
import java.sql.SQLException;

public class MainMenu {
    private static User loggedInUser;
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();

    public void setup() {


        int choice = Integer.parseInt(ui.getInput("Welcome to ClothesPlease! \n\nPress 1 to Login \nPress 2 to Create an account\n"));
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                CreateAccount.createUser();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                setup();
                break;
        }
    }

    public User login() {
        String username = ui.getInput("Enter your username: ");
        String password = ui.getInput("Enter your password: ");
        Connection connection = null;
        try {
            connection = io.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loggedInUser = io.readUserData(connection, username);

        if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
            ui.displayMessage("Login successful. Welcome back, " + loggedInUser.getUsername() + "!");
        } else {
            ui.displayMessage("Invalid username or password. Please try again.");
            loggedInUser = null;
            login();

        }
        return loggedInUser;
    }
}
