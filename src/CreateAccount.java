import java.util.Scanner;

public class CreateAccount {
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();

    public static void createUser() {

        String username = ui.getInput("Enter your username: ");
        while (!DataValidator.isUsernameValid(username)) {
            username = ui.getInput("Username invalid, please try again");
        }

        String password = ui.getInput("Enter a Password: ");
        while (!DataValidator.isPasswordValid(password)) {
            password = ui.getInput("Password invalid, please try again");
        }

        User newUser = new User(username, password);
        io.saveUserData(newUser);



        getInfoFromUser();

        ui.displayMessage("Account created. Welcome!");
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
