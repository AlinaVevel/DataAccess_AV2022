import java.util.ArrayList;
import java.util.List;

public class Ex_3 {
    public static void main(String[] args) {
        Museum ermitage = new Museum("Ermitage", "Dvortsovaya plaza", "St.Petersburg", "Russia");
        Author leonardo = new Author("Leonardo", "italian");
        Author picasso = new Author("Picasso", "spanish");
        Author glicon = new Author("Aristotel", "greek");
        ArtWork madonna = new Painting("Madonna", leonardo);
        ArtWork doraMaar = new Painting("Dora Maar with cat", picasso);
        ArtWork hercules = new Sculpture("Hercules", glicon);
        Hall italian = new Hall("romano");
        Hall greek = new Hall("Antic");


        ermitage.addHallAtMuseum(italian);
        ermitage.addHallAtMuseum(greek);

        italian.nameArte(madonna);
        italian.nameArte(doraMaar);
        greek.nameArte(hercules);


        for (int i = 0; i < ermitage.getListOfHall().size(); i++) {
            for (int j = 0; j < ermitage.getListOfHall().get(i).artWork.size(); j++) {


                System.out.println(ermitage.getListOfHall().get(i).artWork.get(j).getTitle() + " " + ermitage.getListOfHall().get(i).artWork.get(j).getAuthor());
                List<Hall> art;

                art = ermitage.getListOfHall();


                for (Hall hall : art) {
                    System.out.println(hall.getName());
                    List<ArtWork> work;
                    work = hall.getArtWork();
                    for (ArtWork a : work) {
                        System.out.println(a.getTitle());
                    }
                }

                art.forEach((h) -> {
                    System.out.println(h.getName());
                    List<ArtWork> work;
                    work = h.getArtWork();
                    work.forEach((a) -> {
                        System.out.println(a.getTitle());
                    });
                });


            }
        }
    }
}