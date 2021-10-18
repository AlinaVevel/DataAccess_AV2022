import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    private List<User> users = new ArrayList<>();
    private User user;

    private boolean bfn = false;
    private boolean bln = false;
    private boolean boc = false;
    private boolean bop = false;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        if ("user".equals(qName)) {

            user = new User();


        }

        switch (qName) {

            case "name":
                bfn = true;
                break;

            case "surname":
                bln = true;
                break;

            case "phone":
                bop = true;
                break;

            case "email":
                boc = true;
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bfn) {
            user.setName(new String(ch, start, length));
            bfn = false;
        }

        if (bln) {
            user.setSureName(new String(ch, start, length));
            bln = false;
        }

        if (boc) {
            user.seteMail(new String(ch, start, length));
            boc = false;
        }

        if (bop) {
            user.setTel(new String(ch, start, length));
            bop = false;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        if ("user".equals(qName)) {
            users.add(user);
        }
    }

    public List<User> getUsers() {

        return users;
    }
}