import java.sql.*;
import java.util.ArrayList;

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
                String dirty = resultSet.getString("dirty");
                ui.displayMessage("Here are your dirty cloth"+resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void viewAllClothes() {x
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            ui.displayMessage("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness() + ", Item: " + item);
        }
    }

    public void viewClothesInBasket() {
        System.out.println("Clothes in the Laundry Basket:");
        System.out.println("Dirty Clothes:");
        for (String item : dirtyClothes) {
            ui.displayMessage("Color: " + getColor(item) + ", Cleanliness: " + getCleanliness() + ", Item: " + item);
        }
    }*/

    private void getColor() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE LOWER (color) != 'White'")) {

            while (resultSet.next()) {
                String color=resultSet.getString("color"));
                ui.displayMessage("Here are you color cloth: "+color);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getWhiteCloth() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE LOWER(color) = 'white'")) {

            while (resultSet.next()) {
                String item = resultSet.getString("item");
                ui.displayMessage("White Cloth: " + item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private int getCleanliness() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clothes WHERE dirty is false")) {

            while (resultSet.next()) {
                String dirty = resultSet.getString("dirty");
                ui.displayMessage("here is your Clean cloth: " + resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

       /* public void empty () {
            dirtyClothes.clear();
        */

        public void closeConnection () {
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
}
