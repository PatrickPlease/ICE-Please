public class Socks extends Clothing {

    String typeOfSock;

    public Socks(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, String typeOfSock) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.typeOfSock = typeOfSock;
    }

    public String getTypeOfSock() {
        return typeOfSock;
    }
}
