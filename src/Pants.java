import java.util.Scanner;

public class Pants extends Clothing {
    TextUI ui = new TextUI();
    String typeOfPants;
    String pockets;

    public Pants(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String pockets, String typeOfPants) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.pockets = pockets;
        this.typeOfPants = typeOfPants;
    }

    public String getTypeOfPants(){return typeOfPants;}
    public String getPockets(){return pockets;}

    public Pants createPants(Scanner scanner) {
        ui.displayMessage("Enter pants details:");

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

        ui.displayMessage("Pockets?:");
        String pockets = scanner.nextLine();

        ui.displayMessage("Type of Pants:");
        String typeOfPants = scanner.nextLine();

        return new Pants(0, color, brand, "Pants", seasons, size, material, info, pockets, typeOfPants);
    }
}
