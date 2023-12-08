public class Pants extends Clothing {
    String typeOfPants;
    boolean pockets;

    public Pants(String color, String brand, String clothingType, String seasons, String size, String material, String info, boolean pockets, String typeOfShoes) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.pockets = pockets;
        this.typeOfPants = typeOfShoes;
    }
}
