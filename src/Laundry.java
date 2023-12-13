import java.sql.*;
import java.util.ArrayList;

public class Laundry {
    private Connection connection;
    private ArrayList<String> dirtyClothes;
    TextUI ui = new TextUI();
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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE clothing is not null")) {

            while (resultSet.next()) {
                dirtyClothes.add(resultSet.getString("item"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    /*public void removeColoredClothes(String color, int cleanliness) {
        Iterator<String> iterator = Clothing.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (getColor(item).equalsIgnoreCase(color) && getCleanliness(item) == cleanliness) {
                dirtyClothes.add(item);
                iterator.remove();
                ui.displayMessage("All " + color + " clothes with cleanliness " + cleanliness +
                        " moved to the dirty laundry basket.");
                        */




    public void viewAllClothes() {
        ui.displayMessage("Dirty Clothes:");
        for (String item : dirtyClothes) {
            ui.displayMessage("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
        }

    }

    public void viewClothesInBasket() {
        ui.displayMessage("Clothes in the Laundry Basket:");
        ui.displayMessage("Dirty Clothes:");
        for (String item : dirtyClothes) {
            ui.displayMessage("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness(item) + ", Item: " + item);
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
