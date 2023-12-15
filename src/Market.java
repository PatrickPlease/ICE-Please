import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Quantum changes POGs
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Market {
    private static final TextUI ui = new TextUI();
    private static final DbIO io = new DbIO();
    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
    private static final String USER = "sql11669455";
    private static final String PASSWORD = "dvjB1r36bu";
    private static List<ClothingListing> listings = new ArrayList<>();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Qcode
    public static void getConnectionTest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
            String user = "sql11669455";
            String password = "dvjB1r36bu";
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT listings.listing_id, listings.price, users.username " +
                    "FROM listings " +
                    "INNER JOIN users ON listings.seller_id = users.user_id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("listing_id");
                String seller = resultSet.getString("username");
                double price = resultSet.getDouble("price");
                ui.displayMessage("#" + id + "\nSeller: " + seller + "\nPrice: " + price);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        handleUserChoice(scanner);
    }

    private static int displayMenu(Scanner scanner) {
        ui.displayMessage("Welcome to the Market!");
        ui.displayMessage("Please select an option:");
        ui.displayMessage("1. View available clothing items for sale");
        ui.displayMessage("2. Buy a clothing item");
        ui.displayMessage("3. Sell an item");
        ui.displayMessage("4. Borrow a clothing item");
        ui.displayMessage("5. Donate a clothing item");
        ui.displayMessage("6. Exit the Market");
        return scanner.nextInt();
    }


    private static void listAvailableClothingItems() {
//viewListings();
        getConnectionTest();
    }

    private static void buyClothingItem(Scanner scanner) {

        buyClothing(scanner);
    }

    private static void donateClothingItem(Scanner scanner) {

        donateClothing(scanner);
    }

    private static void borrowClothingItem(Scanner scanner) {

        borrowClothing(scanner);
    }

    private static void exitMarket() {

        ui.displayMessage("Exiting the Market...");
    }

    private static void handleUserChoice(Scanner scanner) {
        int choice = displayMenu(scanner);
        switch (choice) {
            case 1:
                listAvailableClothingItems();
                ui.displayMessage("\n");
                handleUserChoice(scanner);
                break;
            case 2:
                buyClothingItem(scanner);
                ui.displayMessage("\n");
                handleUserChoice(scanner);
                break;
            case 3:
                addListingItem(scanner);

                break;
            case 4:
                donateClothingItem(scanner);
                ui.displayMessage("\n");
                handleUserChoice(scanner);
                break;
            case 5:
                borrowClothingItem(scanner);
                ui.displayMessage("\n");
                handleUserChoice(scanner);

                break;
            case 6:
                exitMarket();
                break;

            default:
                ui.displayMessage("Invalid choice. Please try again.");
                handleUserChoice(scanner);
                break;
        }
    }

    private static User getUserById(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM users WHERE user_id =?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        return new User(username, password, "");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static Clothing getClothingById(int clothingId) {
        // Implement logic to retrieve a clothing item from the database based on the ID
        // ...

        return null;
    }
    private static void addListingToDatabase(ClothingListing listing) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
            String user = "sql11669455";
            String password = "dvjB1r36bu";
            connection = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO listings (seller_id, clothing_id, price) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, listing.getSeller().getId());
                statement.setInt(2, listing.getClothingItem().getId());
                statement.setDouble(3, listing.getPrice());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void addListingItem(Scanner scanner) {
        // Collect information about the new listing from the user
        ui.displayMessage("Enter the clothing item ID:");
        int clothingItemId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        ui.displayMessage("Enter the price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        User seller = getUserById(1);

        // Create a new ClothingListing object
        ClothingListing newListing = new ClothingListing(seller, getClothingById(clothingItemId), price);

        // Add the new listing to the database
        addListingToDatabase(newListing);

        // Add the new listing to the local listings list
        listings.add(newListing);

        // Display a success message
        ui.displayMessage("Item listed successfully!");
    }

    public static void buyClothing(Scanner scanner) {
        ui.displayMessage("Enter the ID of the listing you want to buy:");
        int listing_id = scanner.nextInt();
        scanner.nextLine();
        ClothingListing listing = getListingById(listing_id);
        if (listing!= null) {
            ui.displayMessage("Enter your name:");
            String name = scanner.nextLine();
            ui.displayMessage("Enter your password:");
            String password = scanner.nextLine();
            User buyer = getUserByCredentials(name, password);
            if (buyer!= null) {
                buyListing(buyer, listing);
            } else {
                ui.displayMessage("Invalid user credentials. Purchase cancelled.");
            }
        } else {
            ui.displayMessage("Invalid listing ID. Purchase cancelled.");
        }
    }

    private static ClothingListing getListingById(int listing_id) {
        for (ClothingListing listing : listings) {
            if (listing.getId() == listing_id) {
                return listing;
            }
        }
        return null;
    }

    private static User getUserByCredentials(String name, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/users", "sql11669455", "dvjB1r36bu")) {
            String sql = "SELECT * FROM users WHERE username =? AND password =?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int userId = resultSet.getInt("id");
                        return getUserById(userId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void buyListing(User buyer, ClothingListing listing) {
        if (listings.contains(listing)) {
            double price = listing.getPrice();
            listings.remove(listing);
            // Add logic to store the purchase in buy history
            addToBuyHistory(buyer, listing.getClothingItem());
            ui.displayMessage("Item purchased successfully!");
        } else {
            ui.displayMessage("Item is not available for purchase.");
        }
    }

    private static void addToBuyHistory(User buyer, Clothing clothingItem) {
        // Implement logic to store the purchase in buy history
        // ...
    }


    public static void donateClothing(Scanner scanner) {
        System.out.println("Enter the ID of the clothing item you want to donate:");
        int clothingId = scanner.nextInt();
        scanner.nextLine();
        Clothing clothingItem = getClothingById(clothingId);
        if (clothingItem != null) {
            System.out.println("Enter the ID of the user you want to donate the item to:");
            int userId = scanner.nextInt();
            scanner.nextLine();
            User donor = getUserById(userId);
            if (donor != null) {
                ui.displayMessage("Item donated successfully!");
            } else {
                System.out.println("Invalid user ID. Donation cancelled.");
            }
        } else {
            System.out.println("Invalid clothing ID. Donation cancelled.");
        }
    }

    public static void borrowClothing(Scanner scanner) {
        System.out.println("Enter the ID of the clothing item you want to borrow:");
        int clothingId = scanner.nextInt();
        scanner.nextLine();
        Clothing clothingItem = getClothingById(clothingId);
        if (clothingItem != null) {
            System.out.println("Enter the ID of the user you want to borrow the item from:");
            int userId = scanner.nextInt();
            scanner.nextLine();
            User borrower = getUserById(userId);
            if (borrower != null) {
                ui.displayMessage("Item borrowed successfully!");
            } else {
                System.out.println("Invalid user ID. Borrowing cancelled.");
            }
        } else {
            System.out.println("Invalid clothing ID. Borrowing cancelled.");
        }
    }

}