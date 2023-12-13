import java.util.Scanner;

public class CreateAccount {
    private static TextUI ui = new TextUI();

    public static void createUser() {
        Scanner scanner = new Scanner(System.in);

        ui.displayMessage("Enter a Username:");
        String username = scanner.nextLine();
        while (!DataValidator.isUsernameValid(username)) {
            ui.displayMessage("Username invalid, please try again");
            username = scanner.nextLine();
        }

        ui.displayMessage("Enter a Password:");
        String password = scanner.nextLine();
        while (!DataValidator.isPasswordValid(password)) {
            ui.displayMessage("Password invalid, please try again");
            password = scanner.nextLine();
        }

        User newUser = new User(username, password);
        FileIO.saveUserData(newUser);

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

}