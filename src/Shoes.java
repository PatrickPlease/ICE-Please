public class Shoes extends Clothing {

    private String typeOfShoes;

    public Shoes(String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfShoes) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.typeOfShoes = typeOfShoes;
    }
}