public class Dress extends Clothing {

    String typeOfDress;

    public Dress(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfDress) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfDress = typeOfDress;
    }

    public String getTypeOfDress() {
        return typeOfDress;
    }
}