import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {
    private static final TextUI ui = new TextUI();
    private static final DbIO io = new DbIO();
    private static int userId;
    private static User currentUser;
    private static List<ClothingListing> listings = new ArrayList<>();
    private static int displayMenu(Scanner scanner) {
        System.out.println("Welcome to the Market!");
        System.out.println("Please select an option:");
        System.out.println("1. List available clothing items for sale");
        System.out.println("2. Buy a clothing item");
        System.out.println("3. Donate a clothing item");
        System.out.println("4. Borrow a clothing item");
        System.out.println("5. Exit the Market");
        return scanner.nextInt();
    }

    private static void listAvailableClothingItems() {

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
        System.out.println("Exiting the Market...");
    }

    private static void handleUserChoice(Scanner scanner) {
        int choice = displayMenu(scanner);
        switch (choice) {
            case 1:
                listAvailableClothingItems();
                break;
            case 2:
                buyClothingItem(scanner);
                break;
            case 3:
                donateClothingItem(scanner);
                break;
            case 4:
                borrowClothingItem(scanner);
                break;
            case 5:
                exitMarket();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                handleUserChoice(scanner);
                break;
        }
    }


    private static void loadListingsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/sql11669455", "sql11669455", "dvjB1r36bu")) {
            String sql = "SELECT * FROM listings";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    listings = new ArrayList<>();
                    while (resultSet.next()) {
                        User seller = getUserById(resultSet.getInt("seller_id"));
                        Clothing clothingItem = getClothingById(resultSet.getInt("clothing_id"));
                        double price = resultSet.getDouble("price");

                        ClothingListing listing = new ClothingListing(seller, clothingItem, price);
                        listings.add(listing);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User getUserById(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/sql11669455", "sql11669455", "dvjB1r36bu")) {
            String sql = "SELECT * FROM users WHERE user_id =?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String password = resultSet.getString("password");
                        return new User(userId, name, password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve a clothing item from the database by ID
    private static Clothing getClothingById(int clothingId) {
        // Implement logic to retrieve a clothing item from the database based on the ID
        // ...

        return null;
    }

    public static void sellClothing(User seller, Clothing clothingItem, double price) {
        ClothingListing newListing = new ClothingListing(seller, clothingItem, price);
        addListingToDatabase(newListing);
        listings.add(newListing);
        ui.displayMessage("Item listed for sale successfully!");
    }


    private static void addListingToDatabase(ClothingListing listing) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/sql11669455", "sql11669455", "dvjB1r36bu")) {
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

    public static void viewListings() {
        ui.displayMessage("Loading listings from database...");
        if (listings.isEmpty()) {
            loadListingsFromDatabase();
        }
        ui.displayMessage("Available Listings:");
        for (ClothingListing listing : listings) {
            System.out.println(listing);
        }
    }

    public static void buyClothing(Scanner scanner) {
        System.out.println("Enter the ID of the listing you want to buy:");
        int listingId = scanner.nextInt();
        scanner.nextLine();
        ClothingListing listing = getListingById(listingId);
        if (listing!= null) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            User buyer = getUserByCredentials(name, password);
            if (buyer!= null) {
                buyListing(buyer, listing);
            } else {
                System.out.println("Invalid user credentials. Purchase cancelled.");
            }
        } else {
            System.out.println("Invalid listing ID. Purchase cancelled.");
        }
    }

    private static ClothingListing getListingById(int listingId) {
        for (ClothingListing listing : listings) {
            if (listing.getId() == listingId) {
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