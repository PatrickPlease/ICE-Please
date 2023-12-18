import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarketPlace {
    private static final TextUI ui = new TextUI();
    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
    private static final String USER = "sql11669455";
    private static final String PASSWORD = "dvjB1r36bu";
    private List<ClothingListing> listings = new ArrayList<>();

    public MarketPlace() {
        handleUserChoice();
    }

    public static void main(String[] args) {
        MarketPlace mp = new MarketPlace();
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void printListing(ClothingListing listing) {
        ui.displayMessage(listing.getId() + ". " + listing.getClothingItem().getColor() + " " + listing.getClothingItem().getClothingType() + " " + listing.getPrice() + ",- " + listing.getDescription());
    }

    private static void listAvailableClothingItems() {

        String sql = "SELECT listings.*, users.*, clothes.* " +
                "FROM listings " +
                "INNER JOIN users ON listings.seller_id = users.user_id " +
                "LEFT JOIN clothes ON listings.clothing_id = clothes.clothing_id";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"));
                Clothing clothingItem = null;

                switch(resultSet.getString("clothingType").toLowerCase()) {
                    case "shirt":
                        clothingItem = new Shirt(resultSet.getInt("clothing_id"), resultSet.getString("color"), resultSet.getString("brand"), resultSet.getString("clothingType"), resultSet.getString("seasons"), resultSet.getString("size"), resultSet.getString("material"), resultSet.getString("info"), resultSet.getString("sleeveLength"), resultSet.getString("neck"), resultSet.getString("typeOfShirt"));
                        break;
                    case "pants":
                        clothingItem = new Pants(resultSet.getInt("clothing_id"), resultSet.getString("color"), resultSet.getString("brand"), resultSet.getString("clothingType"), resultSet.getString("seasons"), resultSet.getString("size"), resultSet.getString("material"), resultSet.getString("info"), resultSet.getString("pockets"), resultSet.getString("typeOfPants"));
                        break;
                    default:
                        throw new RuntimeException("Invalid clothing item! " + resultSet.getString("clothingType"));
                }

                double price = resultSet.getDouble("price");
                ClothingListing listing = new ClothingListing(resultSet.getInt("listing_id"), user, clothingItem, price, resultSet.getString("description"));
                printListing(listing);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void sellListing() {
        String color = ui.getInput("What color is the item?");
        String clothingType = ui.getInput("What type of clothing is your item?");
        String description = ui.getInput("Describe your item.");
        String priceInput = ui.getInput("Enter the cost");
        double price = 0;

        try {
            price = Double.parseDouble(priceInput);
        } catch (NumberFormatException e) {
            ui.displayMessage("'" + priceInput + "' is not a valid number.");
            sellListing();
        }

        Clothing clothing = null;

        switch(clothingType.toLowerCase()) {
            case "shirt":
                clothing = new Shirt(0, color, "", "Shirt", "", "", "", "", "", "", "");
                break;
            case "pants":
                clothing = new Pants(0,color , "", "Pants","" ,"" ,"" , "", "", "");
                break;
            default:
                throw new RuntimeException("Invalid clothing item! " + clothingType);
        }

        saveClothing(clothing);

        User user = UserManager.loggedInUser;
        if(user == null) {
            user = new User("TestUser", "TestPassword","test@email.com");
            user.setUser_id(5);
        }

        ClothingListing listing = new ClothingListing(0, user, clothing, price, description);

        saveListing(listing);




    }

    private static void saveClothing(Clothing clothing) {
        String sql = "INSERT INTO clothes(color, clothingType) VALUES(?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            statement.setString(1, clothing.getColor());
            statement.setString(2, clothing.getClothingType());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                clothing.setClothing_id(rs.getInt(1));
            }

            ui.displayMessage("Clothing created with ID: " + clothing.getClothing_id());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveListing(ClothingListing listing) {
        String sql = "INSERT INTO listings(seller_id, clothing_id, price, description) VALUES(?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            statement.setInt(1, listing.getSeller().getUser_id());
            statement.setInt(2, listing.getClothingItem().getClothing_id());
            statement.setDouble(3, listing.getPrice());
            statement.setString(4, listing.getDescription());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                listing.setId(rs.getInt(1));
            }

            ui.displayMessage("Listing created with ID: " + listing.getId());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int displayMenu() {
        ui.displayMessage("");
        ui.displayMessage("Welcome to the Market!");
        ui.displayMessage("Please select an option:");
        ui.displayMessage("1. List available clothing items for sale");
        ui.displayMessage("2. Buy a clothing item");
        ui.displayMessage("3. Sell an item");
        ui.displayMessage("4. Exit the Market");

        String input = ui.getInput("Select category by number:");
        try {
            int selected = Integer.parseInt(input);
            return selected;
        } catch (NumberFormatException e) {
           ui.displayMessage("'" + input + "' is not a valid number.");
           return displayMenu();
        }
    }

    private static void handleUserChoice() {
        int choice = displayMenu();
        switch (choice) {
            case 1:
                listAvailableClothingItems();

                handleUserChoice();
                break;
            case 2:
                buyClothingItem();
                handleUserChoice();
                break;
            case 3:
                sellListing();

                handleUserChoice();

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

            default:
                System.out.println("Invalid choice. Please try again.");
                handleUserChoice();
                break;
        }
    }


}
