import java.util.List;

public class ClothingListing {
    private User seller;
    private Clothing clothingItem;
    private double price;

    public ClothingListing(User seller, Clothing clothingItem, double price) {
        this.seller = seller;
        this.clothingItem = clothingItem;
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public Clothing getClothingItem() {
        return clothingItem;
    }

    public double getPrice() {
        return price;
    }

    // Mark the listing as sold
    public void markAsSold(List<ClothingListing> listings) {
        listings.remove(this);
    }

    @Override
    public String toString() {
        return String.format("Listing: %s - %s - $%.2f", seller.getUsername(), clothingItem.getClothingType(), price);
    }
}
