import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        // Create a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455", "sql11669455", "dvjB1r36bu");

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute a SQL query
            String query = "SELECT * FROM clothes       ";
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                // Retrieve data from each row
                String clothing_id = rs.getString("clothing_id");
                String info = rs.getString("info");
                // ...

                // Print the data
                System.out.println("Wardrobe id: " + clothing_id);
                System.out.println("Info: " + info);
                // ...
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}