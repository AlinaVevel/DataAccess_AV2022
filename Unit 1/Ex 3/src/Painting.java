public class Painting extends ArtWork {
    protected TypeOfPainting type;
    protected String format;



    public TypeOfPainting getType() {
        return type;
    }

    public void setType(TypeOfPainting type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
