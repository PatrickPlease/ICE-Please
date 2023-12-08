public class Shirt extends Clothing {

    String sleeveLength;
    String neck;
    String typeOfShirt;

    public Shirt(String color, String brand, String clothingType, String seasons, String size, String material, String info, String sleeveLength, String neck, String typeOfShirt) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.sleeveLength = sleeveLength;
        this.neck = neck;
        this.typeOfShirt = typeOfShirt;
    }
}
