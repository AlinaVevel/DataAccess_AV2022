import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Scanner;

public class ArtWork {
    protected String code;
    protected String title;
    protected Author author;
    protected Date data;
    protected Style style;


    public ArtWork(String title, Author author, Date data, String code, Style style){

        this.code = code;
        this.title = title;
        author.setNameForArtWork(this);
        this.data = data;
        this.style = style;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setNameAutor(String name, Author author){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
