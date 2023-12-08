public class Suits extends Clothing{

    String typeOfSuit;

    public Suits(String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfSuit) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.typeOfSuit = typeOfSuit;
    }
}
