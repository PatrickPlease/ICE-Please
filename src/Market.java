import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Market {
    private static List<ClothingListing> listings = new ArrayList<>();

    private static final String JDBC_URL = "jdbc:mysql://your_database_host:3306/listings";
    private static final String DB_USER = "sql11669455";
    private static final String DB_PASSWORD = "dvjB1r36bu";

    // Method to retrieve listings from the database
    public static void loadListingsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/listings", "sql11669455", "dvjB1r36bu")) {
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

    // Method to retrieve a user from the database by ID
    private static User getUserById(int userId) {
        // Implement logic to retrieve a user from the database based on the ID
        // ...

        return null;
    }

    // Method to retrieve a clothing item from the database by ID
    private static Clothing getClothingById(int clothingId) {
        // Implement logic to retrieve a clothing item from the database based on the ID
        // ...

        return null;
    }

    // Method to sell clothing and add a new listing to the database
    public static void sellClothing(User seller, Clothing clothingItem, double price) {
        // Create a new listing with the seller's user ID
        ClothingListing newListing = new ClothingListing(seller, clothingItem, price);

        // Add the listing to the database
        addListingToDatabase(newListing);

        System.out.println("Item listed for sale successfully!");
    }

    // Method to add a new listing to the database
    private static void addListingToDatabase(ClothingListing listing) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://your_database_host:3306/marketplace_listings", "sql11669455", "dvjB1r36bu")) {
            String sql = "INSERT INTO listings (seller_id, clothing_id, price) VALUES (?, ?, ?)";
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

    // Method to view listings
    public static void viewListings() {
        System.out.println("Loading listings from database...");
        if (listings.isEmpty()) {
            loadListingsFromDatabase();
        }
        System.out.println("Available Listings:");
        for (ClothingListing listing : listings) {
            System.out.println(listing);
        }
    }

    // Method to buy a listing
    public static void buyListing(User buyer, ClothingListing listing) {
        if (listings.contains(listing)) {
            double price = listing.getPrice();

            listings.remove(listing);

            // Add logic to store the purchase in buy history
            addToBuyHistory(buyer, listing.getClothingItem());

            System.out.println("Item purchased successfully!");
        } else {
            System.out.println("Item is not available for purchase.");
        }
    }

    // Method to add to buy history (to be implemented)
    private static void addToBuyHistory(User buyer, Clothing clothingItem) {
        // Implement logic to store the purchase in buy history
        // ...
    }



    public static void donateClothing() {

    }

    public static void borrowClothing() {

    }

    public static void addToBuyHistory() {

    }

    public static void addToSellHistory() {

    }

    public static void chatWindow() {

    }

    public static void savedListing() {

    }


}
