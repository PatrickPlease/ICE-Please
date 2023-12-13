import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Wardrobe wardrobe = new Wardrobe();
        User currentUser = CreateAccount.createUser();
        wardrobe.setCurrentUser(currentUser);



        Market market = new Market();
        market.viewListings();

        Connection connection = null;
        wardrobe.createOutfit();

    }
}