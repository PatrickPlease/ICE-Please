import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbIO {

    static TextUI ui = new TextUI();

    static Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455", "sql11669455", "dvjB1r36bu");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void driver() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select email, password, user_name, user_id FROM users");
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                String user_id = resultSet.getString("user_id");

                System.out.println(user_id + " | Username:" + user_name + ", Password: " + password + "email: " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User readUserData(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                return new User(resultSet.getString("username"), password);
            }
        } catch (SQLException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserData(User user) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Datafile: " + e.getMessage());
        }
    }

    public static List<String> readUserWatchlist(String username) {
        try (Scanner scanner = new Scanner(new File("data/UserData/" + username + "_UserData.txt"))) {
            List<String> watchlist = new ArrayList<>();
            while (scanner.hasNextLine()) {
                watchlist.add(scanner.nextLine());
            }
            return watchlist;
        } catch (FileNotFoundException e) {
            System.out.println("Watchlist file not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserWatchlist(String username, List<String> watchlist) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + username + "_UserData.txt"))) {
            for (String watchlistItem : watchlist) {
                pWriter.println(watchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }

    public static void addToWatchlist(User user, String newWatchlistItem) {
        try (PrintWriter pWriter = new PrintWriter(new FileWriter("data/UserData/" + user.getUsername() + "_UserData.txt", true))) {
            if (user != null) {
                pWriter.println(newWatchlistItem);
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the Watchlist file: " + e.getMessage());
        }
    }
}