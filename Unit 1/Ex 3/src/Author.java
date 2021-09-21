import java.util.ArrayList;
import java.util.List;

public class Author {
    protected String name;
    protected String nationality;
    protected List<ArtWork> listOfArtWorks;

    public Author(String name, String nationality){
        this.nationality = nationality;;
        this.name = name;
    }

    public Author(List<ArtWork> listOfArtWorks){
        listOfArtWorks = new ArrayList<>();
    }

    public void addArt(ArtWork work){
        work.setAuthor(this);
        listOfArtWorks.add(work);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public ArrayList<ArtWork> getArt() {
        return (ArrayList<ArtWork>) listOfArtWorks;
    }

    public void setArt(ArrayList<ArtWork> listOfArtWorks) {
        this.listOfArtWorks = listOfArtWorks;
    }
}
