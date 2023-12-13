public class Dress extends Clothing {

    String typeOfDress;
    String dressLength;

    public Dress(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String dressLength, String typeOfDress) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfDress = typeOfDress;
        this.dressLength = dressLength;
    }

    public String getTypeOfDress() {
        return typeOfDress;
    }

    public String getDressLength() {
        return dressLength;
    }
}