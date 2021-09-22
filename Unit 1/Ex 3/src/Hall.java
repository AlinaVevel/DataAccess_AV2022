import java.util.ArrayList;
import java.util.List;

public class Hall {
    protected String name;
    protected List<ArtWork> artWork;
    protected Museum museum;

    public Museum getMuseum() {
        return museum;
    }

    public Hall(String name){

        this.name = name;
        artWork = new ArrayList<>();
    }



    public void setMuseum(Museum museum) {
        this.museum = museum;
    }



    public List<ArtWork> getArtWork() {
        return artWork;
    }

    public void nameArte(ArtWork work){
        work.isInHall(this);
        artWork.add(work);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void isInMuseum(Museum museum) {
        this.museum = museum;
    }
}
