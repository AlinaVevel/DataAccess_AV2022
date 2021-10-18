import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Prueba {


    public static void main(String[] args) {
        try {
            SAXParser saxParser = SAXParserFactory.
                    newInstance().newSAXParser();
            saxParser.parse("contactList.xml", new
                    XMLHandler());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
