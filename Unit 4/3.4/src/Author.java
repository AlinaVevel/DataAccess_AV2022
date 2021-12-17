import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {
    protected String name;
    protected String nationality;
    protected ArtWork work;
    protected ArrayList<ArtWork> listOfArtWorks;

    public ArrayList<ArtWork> getListOfArtWorks() {
        return listOfArtWorks;
    }

    public void setListOfArtWorks(ArrayList<ArtWork> listOfArtWorks) {
        this.listOfArtWorks = listOfArtWorks;
    }

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

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", work=" + work +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArtWork getWork() {
        return work;
    }

    public void setWork(ArtWork work) {
        this.work = work;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) && Objects.equals(nationality, author.nationality) && Objects.equals(work, author.work);
    }


}