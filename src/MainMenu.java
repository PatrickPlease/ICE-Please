import java.sql.Connection;
import java.sql.SQLException;

public class MainMenu {
    public static User loggedInUser;
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();

    public User setup() {


        int choice = Integer.parseInt(ui.getInput("\nWelcome to ClothesPlease! \n\nPress 1 to Login \nPress 2 to Create an account\n"));
        switch (choice) {
            case 1:
                loggedInUser = login();
                break;
            case 2:
                loggedInUser = CreateAccount.createUser();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                setup();
                break;
        }
        menu();
        return loggedInUser;
    }

    public void menu() {
        int choiceMenu = Integer.parseInt(ui.getInput(
                "Menu: \n1 - Wardrobe\n2 - Laundry\n3 - Market\n4 - Settings"));
        switch (choiceMenu) {
            case 1:
                Wardrobe();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                settingsMenu();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                menu();
                break;
        }
    }

    public void settingsMenu() {
        ui.displayMessage(loggedInUser.getUsername() + "'s Profile Settings:");
        int choiceSettingsMenu = Integer.parseInt(ui.getInput(
                "\n1 - Change Password or Email\n2 - Notification settings\n3 - Log out\n4 - EULA \n\n5 - Back to main page"));
        switch (choiceSettingsMenu) {
            case 1:
                UserManager.changeUserSettings();
                break;
            case 2:

                break;
            case 3:
                setup();
                break;
            case 4:

                break;
            case 5:
                menu();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                settingsMenu();
                break;
        }
    }

    public void Wardrobe(){
        ui.displayMessage(loggedInUser.getUsername() + "'s Wardrobe:");
        int choiceWardrobe = Integer.parseInt(ui.getInput(
                "\n1 - Create Outfit\n2 - \n3 - Add Clothing To Wardrobe\n4 - \n\n5 - Back to main page"));
        switch (choiceWardrobe) {
            case 1:
                loggedInUser.wardrobe.createOutfit();
               // Wardrobe.createOutfit();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                menu();
                break;
            default:
                ui.displayMessage("Invalid choice. Please try again.");
                Wardrobe();
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

        int user_id = io.getUserId(connection,username,password);

        if (loggedInUser != null && loggedInUser.getPassword().equals(password) && user_id != -1) {
            ui.displayMessage("Login successful. Welcome back, " + loggedInUser.getUsername() + "!");
        } else {
            ui.displayMessage("Invalid username or password. Please try again.");
            loggedInUser = null;
            login();

        }
        return loggedInUser;
    }
}
