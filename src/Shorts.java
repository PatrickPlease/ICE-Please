public class Shorts extends Clothing {

    String typeOfShorts;

    public Shorts(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfShorts) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfShorts = typeOfShorts;
    }
    public String getTypeOfShorts() {
        return typeOfShorts;
    }
}
