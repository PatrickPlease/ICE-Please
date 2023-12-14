import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DbIO io = new DbIO();
        MainMenu mainMenu = new MainMenu();
        User loggedInUser = mainMenu.login();

        if(loggedInUser!=null){
            System.out.println("Userloggedin:"+loggedInUser.getUsername());

            Wardrobe wardrobe = new Wardrobe(loggedInUser.getUser_id());
            Connection connection=null;
            try{
                connection=io.getConnection();
            }catch(SQLException e){
//Handletheexceptionorprintthestacktrace
                e.printStackTrace();
            }
            wardrobe.addClothingToWardrobe(connection);
        }else{
            System.out.println("Usernotloggedin.");
        }

    }
}