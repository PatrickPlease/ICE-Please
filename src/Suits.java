public class Suits extends Clothing{

    String typeOfSuit;

    public Suits(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfSuit) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfSuit = typeOfSuit;
    }

    public String getTypeOfSuit() {
        return typeOfSuit;
    }
}
