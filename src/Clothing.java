public abstract class Clothing {

    private static int nextId = 1; // Used to assign unique IDs to clothing items
    private int id;
    private String name;


    public Clothing(String name /*, other fields... */) {
        setId(nextId++);
        setName(name);
        // Set other fields...
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int clothing_id;
    private String color;
    private String brand;
    private String clothingType;
    private String seasons;
    private String size;
    private String material;
    private String info;

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
        this.clothing_id = clothingId;
    }
}
