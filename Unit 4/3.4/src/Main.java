import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;


public class Main {
    static ObjectContainer db = null;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Menu();
        String userInput = sc.nextLine();
        while(!userInput.equals("0")){
            switch(userInput){
                case "1":
                    System.out.println("Input code of author");
                    String code = sc.nextLine();
                    System.out.println("Input name of author");
                    String name = sc.nextLine();
                    System.out.println("Input nationality of author");
                    String nationality = sc.nextLine();
                    Author author = new Author(code, name, nationality);

                    try {
                        db = Db4o.openFile("Museum.dat");
                        db.set(author);
                        db.commit();
                    }
                    finally {
                        if (db != null)
                            db.close();
                    }

                    db = Db4o.openFile("Museum.dat");
                    ObjectSet persons = db.get( new Author() );
                    while (persons.hasNext())
                        System.out.println(persons.next());
                    db.close();

                    Menu();
                    userInput = sc.nextLine();
                    break;
                case "2":
                    MenuCreateArtWork();
                    userInput = sc.nextLine();
                    switch (userInput){
                        case "1":
                            System.out.println("Input code of author");
                            userInput = sc.nextLine();
                            if(!SearchAnAuthor(userInput)){
                                System.out.println("No existe");
                            }
                            else{
                                System.out.println("Existe");
                            }
                    }
            }
        }


    }

    public static void Menu(){
        System.out.println("1. CREATE AN AUTHOR");
        System.out.println("2. CREATE AN ARTWORK");
        System.out.println("3. SEARCH AN ARTWORK");
        System.out.println("0. EXIT");
    }

    public static void MenuCreateArtWork(){
        System.out.println("Create ArtWork");
        System.out.println("Create Sculpture");
        System.out.println("Create Painting");
    }

    public static boolean SearchAnAuthor(String code){
        boolean result = false;
        db = Db4o.openFile("Museum.dat");
        Author author = new Author(code, null,null);
        if(db.get(author).hasNext() == true){
            result = true;
        }
        return result;

    }


}
