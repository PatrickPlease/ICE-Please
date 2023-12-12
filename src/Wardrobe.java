import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wardrobe {
    private User currentUser;
    Shirt shirt;
    TextUI ui = new TextUI();
    DbIO io = new DbIO();
    private int user_id;

    public Outfit createOutfit() {
        List<Clothing> outfitItems = new ArrayList<>();

        try (Connection connection = io.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            String clothingType = "";

            while (true) {
                ui.displayMessage("1. Choose shirt\n2. Choose pants\n3. Choose suit\n4. Choose dress\n5. Choose shoes\n6. Done");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        clothingType = "Shirt";
                        break;
                    case 2:
                        clothingType = "Pants";
                        break;
                    case 3:
                        clothingType = "Suit";
                        break;
                    case 4:
                        clothingType = "Dress";
                        break;
                    case 5:
                        clothingType = "Shoes";
                        break;
                    case 6:
                        creationOptions(scanner, connection, outfitItems);
                        return new Outfit(outfitItems);
                    default:
                        ui.displayMessage("Invalid choice");
                        continue;
                }

                if (!clothingType.isEmpty()) {
                    ui.displayMessage("Here are your " + clothingType);
                    io.showClothes(connection, clothingType);

                    ui.displayMessage("Enter the ID of the chosen " + clothingType + ": ");
                    int clothing_id = scanner.nextInt();
                    scanner.nextLine();

                    Clothing chosenClothing = io.getClothingById(connection, clothing_id);
                    outfitItems.add(chosenClothing);

                    ui.displayMessage("Choice saved.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Outfit(outfitItems);
    }

    private void creationOptions(Scanner scanner, Connection connection, List<Clothing> outfitItems) {
        ui.displayMessage("Outfit items:");

        for (Clothing clothing : outfitItems) {
            ui.displayMessage("- " + clothing.getColor() + " " + clothing.getClothingType());
        }

        ui.displayMessage("1. Save outfit\n2. Add outfit to favourites\n3. Go to wardrobe");
        int finalChoice = scanner.nextInt();
        scanner.nextLine();

        switch (finalChoice) {
            case 1:
                io.saveOutfit(connection, user_id, outfitItems);
                ui.displayMessage("Outfit saved.");
                break;
            case 2:

                break;
            case 3:
                outfitItems.clear();
                break;
            case 4:
                //tilbage til wardrobe menuen
                break;
            default:
                ui.displayMessage("Invalid choice");
        }
    }


    public void generateOutfit(){
    }
    public void showPrewornOutfits(){
    }
    public void addOutfitToFavorite(){
    }
    public void addClothingToWardrobe(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        ui.displayMessage("Choose the type of clothing to add:");
        ui.displayMessage("1. Shirt\n2. Pants\n3. Shoes\n4. Suits");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Clothing newClothing = null;

        switch (choice) {
            case 1:
                shirt = shirt.createShirt(scanner);
                newClothing = shirt.createShirt(scanner);
                break;
            case 2:
                //newClothing = pants.createPants(scanner);
                break;
            case 3:
               // newClothing = shoes.createShoes(scanner);
                break;
            case 4:
                //newClothing = suits.createSuits(scanner);
                break;

            default:
                ui.displayMessage("Invalid choice");
                return;
        }

        currentUser.getClothingItems().add(newClothing);

        ui.displayMessage("Clothing added to wardrobe.");

        io.saveClothingToDatabase(connection, newClothing);
    }
    public void removeClothingFromWardrobe(){
    }
    public void addClothingToLaundry(){
    }
    public void setCurrentUser(User currentUser) {
    }
}