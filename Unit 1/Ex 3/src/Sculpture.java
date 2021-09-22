public class Sculpture extends ArtWork {
    protected Material material;
    protected Style place;

    public Sculpture(String title, Author author) {
        super(title, author);
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Style getPlace() {
        return place;
    }

    public void setPlace(Style place) {
        this.place = place;
    }
}
