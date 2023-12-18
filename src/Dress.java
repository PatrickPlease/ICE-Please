import java.util.Scanner;

public class Dress extends Clothing {
    TextUI ui = new TextUI();
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

    public Dress createDress(Scanner scanner) {
        ui.displayMessage("Enter dress details:");

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

        ui.displayMessage("Dress Length:");
        String dressLength = scanner.nextLine();

        ui.displayMessage("Type of Dress:");
        String typeOfDress = scanner.nextLine();

        return new Dress(0, color, brand, "Dress", seasons, size, material, info, dressLength, typeOfDress);
    }
}