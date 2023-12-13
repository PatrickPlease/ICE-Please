public class Shoes extends Clothing {

    private String typeOfShoes;

    public Shoes(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfShoes) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfShoes = typeOfShoes;
    }

    public String getTypeOfShoes() {
        return typeOfShoes;
    }
}