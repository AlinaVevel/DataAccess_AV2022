import java.util.Date;

public class Painting extends ArtWork {
    protected TypeOfPainting type;
    protected Dimensions dimensions;

    public Painting(String title, Author author, Date date, String code, Style style) {
        super(title, author, date,code,style);
    }


    public TypeOfPainting getType() {
        return type;
    }

    public void setType(TypeOfPainting type) {
        this.type = type;
    }


}