class ClothingListing {
    private int id;
    private User seller;

    private String description;
    private Clothing clothingItem;
    private double price;

    public ClothingListing(int id, User seller, Clothing clothingItem, double price, String description) {
        this.id = id;
        this.seller = seller;
        this.clothingItem = clothingItem;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format("Listing ID: %d - Seller: %s - Item: %s - Price: $%.2f - Description: - %s",
                id, seller.getUsername(), clothingItem.getName(), price, description);
    }
}