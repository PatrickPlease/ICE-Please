import java.util.Scanner;

public class Shorts extends Clothing {
    TextUI ui = new TextUI();
    String typeOfShorts;

    public Shorts(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfShorts) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfShorts = typeOfShorts;
    }
    public String getTypeOfShorts() {
        return typeOfShorts;
    }

    public Shorts createShorts(Scanner scanner) {
        ui.displayMessage("Enter shorts details:");

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

        ui.displayMessage("Type of Shorts:");
        String typeOfShorts = scanner.nextLine();

        return new Shorts(0, color, brand, "Shorts", seasons, size, material, info, typeOfShorts);
    }
}
