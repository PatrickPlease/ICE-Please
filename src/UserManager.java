import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserManager {
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();


    public static void changeUserSettings() {
        try (Connection connection = io.getConnection()) {

            ui.displayMessage("Select the setting to change:\n");
            ui.displayMessage("1 - Change Password");
            ui.displayMessage("2 - Change Email\n");

            int choice = Integer.parseInt(ui.getInput("Enter your choice: "));
            switch (choice) {
                case 1:
                    updatePasswordWithConfirmation(connection, MainMenu.loggedInUser.getUsername());
                    break;
                case 2:
                    updateEmailWithConfirmation(connection, MainMenu.loggedInUser.getUsername());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updatePasswordWithConfirmation(Connection connection, String loggedInUsername) {
        String newPassword = ui.getInput("Enter your new password: ");
        if (confirmUpdate("Are you sure you want to change your password?")) {
            DbIO.updatePassword(connection, loggedInUsername, newPassword);
            ui.displayMessage("Password changed successfully, closing program");
        } else {
            ui.displayMessage("Password change canceled.");
        }
    }

    public static void updateEmailWithConfirmation(Connection connection, String loggedInUsername) {
        String newEmail = ui.getInput("Enter your new email: ");
        if (confirmUpdate("Are you sure you want to change your email?")) {
            DbIO.updateEmail(connection, loggedInUsername, newEmail);
            ui.displayMessage("Email changed successfully, closing program");
        } else {
            ui.displayMessage("Email change canceled.");
        }
    }

    private static boolean confirmUpdate(String message) {
        ui.displayMessage(message + " (Type 'yes' to confirm): ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();
        return userInput.equals("yes");
    }

}