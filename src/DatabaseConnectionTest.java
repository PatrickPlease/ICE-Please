import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455", "sql11669455", "dvjB1r36bu");

            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM clothes";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String clothing_id = rs.getString("clothing_id");
                String info = rs.getString("info");

                System.out.println("Wardrobe id: " + clothing_id);
                System.out.println("Info: " + info);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}