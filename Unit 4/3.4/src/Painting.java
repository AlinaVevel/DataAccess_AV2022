import java.time.LocalDate;

class Painting extends ArtWork {
    protected TypeOfPainting type;
    protected Dimensions dimensions;

    public Painting(String title, Author author, LocalDate date, TypeOfPainting type, Dimensions dimensions) {
        super(title, author, date);
        setType(type);
        setDimensions(dimensions);

    }

    public TypeOfPainting getType() {
        return type;
    }

    public void setType(TypeOfPainting type) {
        this.type = type;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }




}