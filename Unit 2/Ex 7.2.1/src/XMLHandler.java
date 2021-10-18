import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLHandler extends DefaultHandler {

    private String name;


    private String lastElementName;
    private String information;

    boolean isPhone = false;


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
        if (lastElementName.equals("phones"))
        {
            isPhone = true;
        }

    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {



        if (qName.equals("phones"))
            isPhone = false;

        if (!lastElementName.isBlank()) {

            if (lastElementName.equals("name")) {
                name = information;



            } else if (lastElementName.equals("surname")) {
                System.out.println(name + " " + information);
                lastElementName = "";
            }
            else if((lastElementName.equals("home") || lastElementName.equals("work")|| lastElementName.equals("cell")) && isPhone){
                System.out.println(lastElementName + ":" + information);
            }
            lastElementName = "";
        }


    }




    @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
        information = new String(ch, start, length);


        }


    }



















