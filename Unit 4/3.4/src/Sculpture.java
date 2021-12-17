import java.util.Date;

public class Sculpture extends ArtWork {
    protected Material material;
    protected double weight;

    public Sculpture(String title, Author author, Date date, String code, Style style) {
        super(title, author, date,code,style);
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }


}