import java.util.Scanner;

public class Suits extends Clothing{
    TextUI ui = new TextUI();
    String typeOfSuit;

    public Suits(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfSuit) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfSuit = typeOfSuit;
    }

    public String getTypeOfSuit() {
        return typeOfSuit;
    }

    public Suits createSuit(Scanner scanner) {
        ui.displayMessage("Enter suit details:");

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

        ui.displayMessage("Type of Suit:");
        String typeOfSuit = scanner.nextLine();

        return new Suits(0, color, brand, "Suits", seasons, size, material, info, typeOfSuit);
    }
}
