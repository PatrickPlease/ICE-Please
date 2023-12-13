import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Laundry {
    private Connection connection;
    private ArrayList<String> dirtyClothes;
    private ArrayList<String> cleanClothes;

    String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/laundry_database";
    String username = "sql11669455";
    String password = "dvjB1r36bu";

    public Laundry() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
            dirtyClothes = new ArrayList<>();
            cleanClothes = new ArrayList<>();
            readDirtyClothesFromDatabase();  // Call the method during initialization if needed
            readCleanClothesFromDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void readDirtyClothesFromDatabase() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE cleanliness = 1")) {

            while (resultSet.next()) {
                dirtyClothes.add(resultSet.getString("item"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readCleanClothesFromDatabase() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE cleanliness = 0")) {

            while (resultSet.next()) {
                cleanClothes.add(resultSet.getString("item"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeColoredClothes(String color, int cleanliness) {
        Iterator<String> iterator = cleanClothes.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (getColor(item).equalsIgnoreCase(color) && getCleanliness(item) == cleanliness) {
                dirtyClothes.add(item);
                iterator.remove();
                System.out.println("All " + color + " clothes with cleanliness " + cleanliness +
                        " moved to the dirty laundry basket.");
            }
        }

        // Update the database after modifying lists
        readDirtyClothesFromDatabase();
        readCleanClothesFromDatabase();
    }

    public void viewAllClothes() {
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            System.out.println("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }

        System.out.println("\nClean Clothes:");
        for (String item : cleanClothes) {
            System.out.println("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }
    }

    public void viewClothesInBasket() {
        System.out.println("Clothes in the Laundry Basket:");
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            System.out.println("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }

        System.out.println("\nClean Clothes:");
        for (String item : cleanClothes) {
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
        cleanClothes.clear();
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
