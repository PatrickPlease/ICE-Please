public abstract class Clothing {

    private String color;
    private String brand;
    private String clothingType;
    private String seasons;
    private String size;
    private String material;
    private String info;

    public Clothing(String color, String brand, String clothingType, String seasons, String size, String material, String info){
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
}
