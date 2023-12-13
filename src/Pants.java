public class Pants extends Clothing {
    String typeOfPants;
    boolean pockets;

    public Pants(int clothing_id, String color, String brand, String clothingType, String seasons, String size, String material, String info, boolean pockets, String typeOfPants) {
        super(clothing_id, color, brand, clothingType, seasons, size, material, info);
        this.pockets = pockets;
        this.typeOfPants = typeOfPants;
    }

    public String getTypeOfPants(){return typeOfPants;}
    public boolean getPockets(){return pockets;}
}
