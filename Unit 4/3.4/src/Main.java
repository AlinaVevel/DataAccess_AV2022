import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import javax.xml.crypto.Data;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ObjectContainer db = null;
        Date data = new Date();

        try {
            db = Db4o.openFile("Museum.dat");
            Author author = new Author("Da Vinchi", "Italian");

            db.commit();
        }
        finally {
            if (db != null)
                db.close();
        }
    }
}
