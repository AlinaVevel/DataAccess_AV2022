import java.util.Scanner;

public class ArtWork {
    protected String title;
    protected Author author;
    protected Hall hall;
    protected Museum museum;

    public ArtWork(String title, Author author){

        this.title = title;
        author.setNameForArtWork(this);
    }
    public void setNameAutor(String name, Author author){

    }
    public void isInHall(Hall hall) {
        this.hall = hall;
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
