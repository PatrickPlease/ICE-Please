public class Socks extends Clothing {

    String typeOfSock;

    public Socks(String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfSock) {
        super(color, brand, clothingType, seasons, size, material, info);
        this.typeOfSock = typeOfSock;
    }
}
