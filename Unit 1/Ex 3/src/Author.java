import java.util.ArrayList;

public class Author {
    protected String name;
    protected String nationality;
    protected ArrayList <String> listOfArts;

    public Author(ArrayList<String> listOfArts){
        listOfArts = new ArrayList<>();
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

    public ArrayList<String> getArt() {
        return listOfArts ;
    }

    public void setArt(ArrayList<String> listOfArts) {
        this.listOfArts = listOfArts;
    }
}
