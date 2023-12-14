import java.sql.*;
import java.util.List;

public class DbIO {
    TextUI ui = new TextUI();
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
    public void saveOutfitToFavorite(Connection connection, int user_id, List<Clothing> outfitItems) {
        String sql = "INSERT INTO fave_outfits (user_id, clothing_id) VALUES (?, ?)";
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

                    ui.displayMessage(id + ". " + color + " " + brand+ " " + material + " " + size + " " + seasons + " " + info);
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
                            String typeOfShirt = resultSet.getString("typeOfShirt");
                            String sleeveLength = resultSet.getString("sleeveLength");
                            String neck = resultSet.getString("neck");
                            return new Shirt(id, color, brand, type, seasons, size, material, info, sleeveLength, neck, typeOfShirt);
                        case "Pants":
                            String pockets = resultSet.getString("pockets");
                            String typeOfPants = resultSet.getString("typeOfPants");
                            return new Pants(id, color, brand, type, seasons, size, material, info, pockets, typeOfPants);
                        case "Shorts":
                            String typeOfShorts = resultSet.getString("typeOfShorts");
                            return new Shorts(id, color, brand, type, seasons, size, material, info, typeOfShorts);
                        case "Shoes":
                            String typeOfShoes = resultSet.getString("typeOfShoes");
                            return new Shoes(id, color, brand, type, seasons, size, material, info, typeOfShoes);
                        case "Socks":
                            String typeOfSock = resultSet.getString("typeOfSock");
                            return new Socks(id, color, brand, type, seasons, size, material, info, typeOfSock);
                        case "Suits":
                            String typeOfSuit = resultSet.getString("typeOfSuit");
                            return new Suits(id, color, brand, type, seasons, size, material, info, typeOfSuit);
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

    public static User readUserData(Connection connection, String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new User(resultSet.getString("username"), password, email);
            }
        } catch (SQLException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;
    }

    public static void saveUserData(Connection connection, User user) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (user != null) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error saving user data to the database: " + e.getMessage());
        }
    }

    public static int getUserId(Connection connection, String username, String password) {
        String query = "SELECT user_id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user_id: " + e.getMessage());
        }
        return -1;  // Return -1 if login fails
    }

    public void saveClothingToDatabase(Connection connection, Clothing clothing) {
        StringBuilder sql = new StringBuilder("INSERT INTO clothes (color, brand, clothingType, seasons, size, material, info");
        StringBuilder values = new StringBuilder("VALUES (?, ?, ?, ?, ?, ?, ?");
        int parameterIndex = 7;

        try {
            if (clothing instanceof Shirt) {
                sql.append(", sleeveLength, neck, typeOfShirt");
                values.append(", ?, ?, ?");
                parameterIndex += 1;
            } else if (clothing instanceof Pants) {
                sql.append(", typeOfPants");
                values.append(", ?");
                parameterIndex += 1;
            } else if (clothing instanceof Shoes) {
                sql.append(", typeOfShoes");
                values.append(", ?");
                parameterIndex += 1;
            } else if (clothing instanceof Shorts) {
                sql.append(", typeOfShorts");
                values.append(", ?");
                parameterIndex += 1;
            } else if (clothing instanceof Dress) {
                sql.append(", dressLength, typeOfPants");
                values.append(", ?, ?");
                parameterIndex += 1;
            } else if (clothing instanceof Suits) {
                sql.append(", typeOfSuit");
                values.append(", ?");
                parameterIndex += 1;
            }

            sql.append(") ").append(values).append(")");

            try (PreparedStatement statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, clothing.getColor());
                statement.setString(2, clothing.getBrand());
                statement.setString(3, clothing.getClothingType());
                statement.setString(4, clothing.getSeasons());
                statement.setString(5, clothing.getSize());
                statement.setString(6, clothing.getMaterial());
                statement.setString(7, clothing.getInfo());

                if (clothing instanceof Shirt) {
                    Shirt shirt = (Shirt) clothing;
                    statement.setString(parameterIndex++, shirt.getSleeveLength());
                    statement.setString(parameterIndex++, shirt.getNeck());
                    statement.setString(parameterIndex, shirt.getTypeOfShirt());
                } else if (clothing instanceof Pants) {
                    Pants pants = (Pants) clothing;
                    statement.setString(parameterIndex, pants.getTypeOfPants());
                } else if (clothing instanceof Shoes) {
                    Shoes shoes = (Shoes) clothing;
                    statement.setString(parameterIndex, shoes.getTypeOfShoes());
                } else if (clothing instanceof Shorts) {
                    Shorts shorts = (Shorts) clothing;
                    statement.setString(parameterIndex, shorts.getTypeOfShorts());
                } else if (clothing instanceof Dress) {
                    Dress dress = (Dress) clothing;
                    statement.setString(parameterIndex++, dress.getDressLength());
                    statement.setString(parameterIndex, dress.getTypeOfDress());
                } else if (clothing instanceof Suits) {
                    Suits suits = (Suits) clothing;
                    statement.setString(parameterIndex, suits.getTypeOfSuit());
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
