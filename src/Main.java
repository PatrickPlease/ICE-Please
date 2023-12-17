import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DbIO io = new DbIO();
        MainMenu mainMenu = new MainMenu();
       // User logininuser = mainMenu.login();
        MainMenu.loggedInUser = mainMenu.setup();
/*
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

    */
    }
}