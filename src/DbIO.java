import java.sql.*;
import java.util.List;

public class DbIO {

    private static final String URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11669455";
    private static final String USER = "sql11669455";
    private static final String PASSWORD = "dvjB1r36bu";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void saveOutfit(Connection connection, int user_id, List<Clothing> outfitItems) {
        String sql = "INSERT INTO outfits (user_id, clothing_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Clothing clothing : outfitItems) {
                statement.setInt(1, user_id);
                statement.setInt(2, clothing.getClothing_id());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showClothes(Connection connection, String clothingType) {
        String sql = "SELECT * FROM clothes WHERE clothingType = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clothingType);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("clothing_id");
                    String color = resultSet.getString("color");
                    String brand = resultSet.getString("brand");
                    String material = resultSet.getString("material");
                    String seasons = resultSet.getString("seasons");
                    String size = resultSet.getString("size");
                    String info = resultSet.getString("info");

                    System.out.println(id + ". " + color + " " + brand+ " " + material + " " + size + " " + seasons + " " + info);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Clothing getClothingById(Connection connection, int clothing_id) {
        String sql = "SELECT * FROM clothes WHERE clothing_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clothing_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("clothing_id");
                    String color = resultSet.getString("color");
                    String brand = resultSet.getString("brand");
                    String type = resultSet.getString("clothingType");
                    String material = resultSet.getString("material");
                    String seasons = resultSet.getString("seasons");
                    String size = resultSet.getString("size");
                    String info = resultSet.getString("info");

                    switch (type) {
                        case "Shirt":
                            return new Shirt(id, color, brand, type, seasons, size, material, info, "", "", "");
                        case "Pants":
                            boolean pockets = resultSet.getBoolean("pockets");
                            return new Pants(id, color, brand, type, seasons, size, material, info, pockets, "");
                        case "Shoes":
                            String typeOfShoes = resultSet.getString("typeOfShoes");
                            return new Shoes(id, color, brand, type, seasons, size, material, info, typeOfShoes);
                        case "Socks":
                            String typeOfSock = resultSet.getString("typeOfSock");
                            return new Socks(id, color, brand, type, seasons, size, material, info, typeOfSock);
                        case "Suits":
                            String typeOfSuit = resultSet.getString("typeOfSuit");
                            return new Suits(id, color, brand, type, seasons, size, material, info, typeOfSuit);
                        // Add other clothing types as needed
                        default:
                            throw new IllegalArgumentException("Unsupported clothing type: " + type);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveClothingToDatabase(Connection connection, Clothing clothing) {
        String sql = "INSERT INTO clothes (color, brand, clothingType, seasons, size, material, info, sleeveLength, neck) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, clothing.getColor());
            statement.setString(2, clothing.getBrand());
            statement.setString(3, clothing.getClothingType());
            statement.setString(4, clothing.getSeasons());
            statement.setString(5, clothing.getSize());
            statement.setString(6, clothing.getMaterial());
            statement.setString(7, clothing.getInfo());

            if (clothing instanceof Shirt) {
                Shirt shirt = (Shirt) clothing;
                statement.setString(8, shirt.getSleeveLength());
                statement.setString(9, shirt.getNeck());
                statement.setString(10, shirt.getTypeOfShirt());
            } else if (clothing instanceof Pants) {
                Pants pants = (Pants) clothing;
                statement.setString(8, pants.getTypeOfPants());
            } else if (clothing instanceof Shoes) {
                Shoes shoes = (Shoes) clothing;
                statement.setString(8, shoes.getTypeOfShoes());
            }

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting clothing failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int clothingId = generatedKeys.getInt(1);
                    clothing.setClothing_id(clothingId);
                } else {
                    throw new SQLException("Inserting clothing failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
