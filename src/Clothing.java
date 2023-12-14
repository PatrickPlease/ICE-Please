public abstract class Clothing {
    private int clothing_id;
    private String color;
    private String brand;
    private String clothingType;
    private String seasons;
    private String size;
    private String material;
    private String info;
    private int user_id;

    public Clothing(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info){
        this.clothing_id = clothing_id;
        this.color = color;
        this.brand = brand;
        this.clothingType = clothingType;
        this.seasons = seasons;
        this.size = size;
        this.material = material;
        this.info = info;
    }

    public String getColor() {
        return color;
    }
    public String getBrand() {
        return brand;
    }
    public String getClothingType() {
        return clothingType;
    }
    public String getSeasons() {
        return seasons;
    }
    public String getSize() {
        return size;
    }
    public String getMaterial() {
        return material;
    }
    public String getInfo() {
        return info;
    }
    public int getClothing_id() { return clothing_id;}

    public void setClothing_id(int clothingId) {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
