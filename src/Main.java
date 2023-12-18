import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DbIO io = new DbIO();
        MainMenu mainMenu = new MainMenu();
        MainMenu.loggedInUser = mainMenu.setup();

    }
}