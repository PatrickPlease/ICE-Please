import java.util.List;

public class Outfit {

    private List<Clothing> outfitItems;

    public Outfit(List<Clothing> items) {
        this.outfitItems = items;
    }

    public List<Clothing> getItems() {
        return outfitItems;
    }
}
