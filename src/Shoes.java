import java.util.Scanner;

public class Shoes extends Clothing {
    TextUI ui = new TextUI();
    private String typeOfShoes;

    public Shoes(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfShoes) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfShoes = typeOfShoes;
    }

    public String getTypeOfShoes() {
        return typeOfShoes;
    }

    public Shoes createShoes(Scanner scanner) {
        ui.displayMessage("Enter shoe details:");

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

        ui.displayMessage("Type of Shoes:");
        String typeOfShoes = scanner.nextLine();

        return new Shoes(0, color, brand, "Shoes", seasons, size, material, info, typeOfShoes);
    }
}