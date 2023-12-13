import java.util.ArrayList;
import java.util.List;

public class Market {


    private static List<Clothing> clothingListings;
    private static List<ClothingListing> listings;
    public static void sellClothing(User seller, Clothing clothingItem, double price) {
        ClothingListing newListing = new ClothingListing(seller, clothingItem, price);

        addListing(newListing);

        System.out.println("Item listed for sale successfully!");
    }

    public static void viewListings() {
        System.out.println("Available Listings:");
        for (ClothingListing listing : listings) {
            System.out.println(listing);
        }
    }

    public static void buyListing(User buyer, ClothingListing listing) {
        if (listings.contains(listing)) {
            double price = listing.getPrice();

            listings.remove(listing);

            addToBuyHistory(buyer, listing.getClothingItem());

            System.out.println("Item purchased successfully!");
        } else {
            System.out.println("Item is not available for purchase.");
        }
    }

    private static void addListing(ClothingListing listing) {
        if (listings == null) {
            listings = new ArrayList<>();
        }

        listings.add(listing);
    }

    public static void donateClothing(User donor, Clothing clothingItem) {

    }

    public static void borrowClothing(User borrower, Clothing clothingItem) {

    }

    public static void addToBuyHistory(User buyer, Clothing clothingItem) {

    }

    public static void addToSellHistory(User seller, Clothing clothingItem) {

    }

    public static void chatWindow(User user1, User user2) {

    }

    public static void savedListing(User user, Clothing clothingItem) {

    }


}
