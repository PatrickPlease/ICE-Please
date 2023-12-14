import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Laundry {
    private Connection connection;
    private ArrayList<String> dirtyClothes;
    private TextUI ui = new TextUI();

    String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
    String username = "sql11669455";
    String password = "dvjB1r36bu";

    public Laundry() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            dirtyClothes = new ArrayList<>();
            readDirtyClothesFromDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void readDirtyClothesFromDatabase() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE dirty is true")) {

            while (resultSet.next()) {
                dirtyClothes.add(resultSet.getString("item"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllClothes() {
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            ui.displayMessage("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }
    }

    public void viewClothesInBasket() {
        System.out.println("Clothes in the Laundry Basket:");
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            System.out.println("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }
    }

    private String getColor(String item) {
        // Extract color logic
        return "Unknown";
    }

    private int getCleanliness(String item) {
        // Extract cleanliness logic
        return 0; // Default to clean
    }

    public void empty() {
        dirtyClothes.clear();
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
