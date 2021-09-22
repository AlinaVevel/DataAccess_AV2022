import java.util.ArrayList;
import java.util.List;

public class Author {
    protected String name;
    protected String nationality;
    protected ArtWork work;

    public Author(String name, String nationality){
        this.nationality = nationality;;
        this.name = name;
    }

    public Author(List<ArtWork> listOfArtWorks){
        listOfArtWorks = new ArrayList<>();
    }



    public void setNameForArtWork (ArtWork work){
        this.work = work;
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


}
