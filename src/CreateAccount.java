import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CreateAccount {
    private static TextUI ui = new TextUI();
    private static DbIO io = new DbIO();
    private static User newUser;

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
        newUser = null;
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

    public static void getInfoFromUser() {
        Scanner scanner = new Scanner(System.in);
        int userId = getUserId(newUser.getUsername(), newUser.getPassword());

        ui.displayMessage("How many times do you use your underwear before washing it?");
        int underwearUsage = getIntInput();

        ui.displayMessage("How many underwear do you have?");
        int totalUnderwear = getIntInput();

        newUser.setUnderwearUsage(underwearUsage);
        newUser.setTotalUnderwear(totalUnderwear);

        saveUnderwearSettings(userId, underwearUsage, totalUnderwear);

        List<Underwear> underwearList = initializeUnderwearList(totalUnderwear);

        int daysToSimulate = 10;
        simulateDaysPassing(underwearList, userId, underwearUsage, daysToSimulate);
    }

    private static List<Underwear> initializeUnderwearList(int totalUnderwear) {
        List<Underwear> underwearList = new ArrayList<>();
        for (int i = 0; i < totalUnderwear; i++) {
            underwearList.add(new Underwear(0,"","","Underwear","","","","",""));
        }
        return underwearList;
    }


    private static void simulateDaysPassing(List<Underwear> underwearList, int userId, int usageFrequency, int daysToSimulate) {
        int totalUnderwear = getUnderwearSettings(userId).getTotalUnderwear();

        for (int day = 1; day <= daysToSimulate; day++) {
            if (day % usageFrequency == 0 && !underwearList.isEmpty()) {
                Underwear usedUnderwear = underwearList.remove(0);
                ui.displayMessage("Day " + day + ": Used one underwear. Added to laundry list.");

                updateUnderwearUsage(userId);
            } else if (totalUnderwear > 0) {
                ui.displayMessage("Day " + day + ": No need to change underwear.");
                totalUnderwear--;
            }
        }
    }

    private static UnderwearSettings getUnderwearSettings(int userId) {
        UnderwearSettings underwearSettings = null;
        try (Connection connection = DbIO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM underwear_settings WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int underwearUsage = resultSet.getInt("underwear_usage");
                    int totalUnderwear = resultSet.getInt("total_underwear");
                    underwearSettings = new UnderwearSettings(underwearUsage, totalUnderwear);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting underwear settings: " + e.getMessage());
        }
        return underwearSettings;
    }

    private static void saveUnderwearSettings(int userId, int underwearUsage, int totalUnderwear) {
        try (Connection connection = DbIO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO underwear_settings (user_id, underwear_usage, total_underwear) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, underwearUsage);
            preparedStatement.setInt(3, totalUnderwear);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving underwear settings: " + e.getMessage());
        }
    }

    private static void updateUnderwearUsage(int userId) {
        try (Connection connection = DbIO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE underwear_settings SET underwear_usage = underwear_usage - 1 WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating underwear usage: " + e.getMessage());
        }
    }

    private static int getUserId(String username, String password) {
        try (Connection connection = DbIO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT user_id FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user_id: " + e.getMessage());
        }
        return -1;
    }

    private static int getIntInput() {
        int value;
        do {
            String input = ui.getInput("Enter a positive number:");
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    ui.displayMessage("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                ui.displayMessage("Please enter a valid number.");
                value = -1;
            }
        } while (value <= 0);

        return value;
    }



    // MainMenu();
}
