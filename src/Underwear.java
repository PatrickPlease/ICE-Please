public class Underwear extends Clothing {

    String typeOfUnderwear;

    public Underwear(String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfUnderwear) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.typeOfUnderwear = typeOfUnderwear;
    }
}
