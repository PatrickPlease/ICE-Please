import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateAccount {
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();

    public static User createUser() {

        String username = ui.getInput("Enter your username: ");
        while (!DataValidator.isUsernameValid(username)) {
            username = ui.getInput("Username invalid, please try again");
        }

        String password = ui.getInput("Enter a Password: ");
        while (!DataValidator.isPasswordValid(password)) {
            password = ui.getInput("Password invalid, please try again");
        }

        String email = ui.getInput("Enter your email: ");
        while (!DataValidator.isEmailValid(email)) {
            email = ui.getInput("Email invalid, please try again");
        }
        User newUser = null;
        try {
            Connection dbConnection = DbIO.getConnection();

            newUser = new User(username, password, email);

            DbIO.saveUserData(dbConnection, newUser);

            ui.displayMessage("Now we need to know more about you");



        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

        getInfoFromUser();

        ui.displayMessage("Account created. Welcome!");

        return newUser;
    }

    public static void getInfoFromUser(){
        TextUI ui = new TextUI();
        Scanner scanner = new Scanner(System.in);
        ui.getInput("How often do you wash your Underwear after using it?");

        String UnderwearWash = scanner.nextLine();
        ui.getInput("How often do you wash your Jeans after using it?");

        String jeansWash= scanner.nextLine();
        ui.getInput("How often do you wash your socks after using it?");

        String socksWash= scanner.nextLine();
        ui.getInput("how often do you wash your shirt after using it?");

        String shirtWash= scanner.nextLine();
    }

    // MainMenu();
}
