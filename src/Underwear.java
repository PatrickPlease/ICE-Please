public class Underwear extends Clothing {

    String typeOfUnderwear;

    public Underwear(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfUnderwear) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfUnderwear = typeOfUnderwear;
    }

    public String getTypeOfUnderwear() {
        return typeOfUnderwear;
    }
}
