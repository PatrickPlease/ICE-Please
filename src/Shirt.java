import java.util.Scanner;

public class Shirt extends Clothing {
    TextUI ui = new TextUI();

    String sleeveLength;
    String neck;
    String typeOfShirt;

    public Shirt(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String sleeveLength, String neck, String typeOfShirt) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.sleeveLength = sleeveLength;
        this.neck = neck;
        this.typeOfShirt = typeOfShirt;
    }
    public String getSleeveLength(){
        return sleeveLength;
    }
    public String getNeck() {
        return neck;
    }
    public String getTypeOfShirt() {
        return typeOfShirt;
    }

    public Shirt createShirt(Scanner scanner) {
        ui.displayMessage("Enter shirt details:");

        ui.displayMessage("Color:");
        String color = scanner.nextLine();

        ui.displayMessage("Brand:");
        String brand = scanner.nextLine();

        ui.displayMessage("Size:");
        String size = scanner.nextLine();

        ui.displayMessage("Seasons:");
        String seasons = scanner.nextLine();

        ui.displayMessage("Material:");
        String material = scanner.nextLine();

        ui.displayMessage("Additional Info:");
        String info = scanner.nextLine();

        ui.displayMessage("Sleeve Length:");
        String sleeveLength = scanner.nextLine();

        ui.displayMessage("Neck:");
        String neck = scanner.nextLine();

        ui.displayMessage("Type of Shirt:");
        String typeOfShirt = scanner.nextLine();

        return new Shirt(0, color, brand, "Shirt", seasons, size, material, info, sleeveLength, neck, typeOfShirt);
    }
}
