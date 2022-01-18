import java.time.LocalDate;

class Sculpture extends ArtWork {
    protected Material material;
    protected Style typeStyle;

    public Sculpture(String title, Author author, LocalDate date, Material material, Style typeStyle) {
        super(title, author, date);
        setMaterial(material);
        setType(typeStyle);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Style getType() {
        return typeStyle;
    }

    public void setType(Style typeStyle) {
        this.typeStyle = typeStyle;
    }
}