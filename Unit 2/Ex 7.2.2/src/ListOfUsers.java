import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfUsers implements Serializable, Colors {
    private ArrayList<User> userList;
    private static final long serialVersionUID = -835882900113176646L;

    public ListOfUsers() {
        userList = new ArrayList<User>();
    }

    public void addUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_CYAN + "Adding user" + ANSI_RESET);
        String answer;
        do {
            User user = new User();
            user.setName();
            user.setSureName();
            System.out.println(ANSI_YELLOW + "Would you like to add email and phone number? " + ANSI_CYAN + " Y-yes, N-no" + ANSI_RESET);
            answer = scanner.nextLine();
            answer.toLowerCase();
            while(!answer.equals("y") && !answer.equals("n")){
                System.out.println(ANSI_RED + "Only Y or N" + ANSI_RESET);
                answer = scanner.nextLine();
            }
            if (answer.equals("y")) {
                user.seteMail();
                user.setTel();
                userList.add(user);
            } else {
                userList.add(user);
            }


            System.out.println(ANSI_YELLOW + "Do you want to add person press " + ANSI_CYAN + " any key" + ANSI_RESET +  " if not press " + ANSI_CYAN + "ENTER" + ANSI_RESET);
            answer = scanner.nextLine();

                try {
                    FileOutputStream fos = new FileOutputStream(new File("listOfUsers.xml"));
                    XMLEncoder encoder = new XMLEncoder(fos);
                    encoder.writeObject(user);
                    encoder.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        while (!answer.isEmpty());
    }

    public void allInfo() {
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).printInfoUser();
            System.out.println("\033[H\033[2J");
        }
    }

    public void searchForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "Input name " + ANSI_RESET);
        String name = scanner.nextLine();
        System.out.print(ANSI_YELLOW + "Input surname" + ANSI_RESET);
        String surname = scanner.nextLine();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(name) && userList.get(i).getSureName().equals(surname)) {
                System.out.println("User found");
                userList.get(i).printInfoUser();
                System.out.println("\033[H\033[2J");
            } else if (i >= userList.size()) {
                System.out.println(ANSI_RED + "User not found" + ANSI_RESET);
            }

        }
    }

    public void fromObjectToXML() {


        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("userList.xml")));

            for(int i = 0; i < userList.size(); i++){
                encoder.writeObject(userList.get(i).getName());
                encoder.writeObject(userList.get(i).getSureName());
                encoder.writeObject(userList.get(i).geteMail());
                encoder.writeObject(userList.get(i).getTel());
            }
            encoder.flush();
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeXMLFile(){
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder build = dFactory.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("userinfo");
            doc.appendChild(root);

            for (int i = 0; i < userList.size(); i++) {

                Element details = doc.createElement("user");
                root.appendChild(details);

                Element name = doc.createElement("name");

                name.appendChild(doc.createTextNode(String.valueOf(userList.get(i).getName())));
                details.appendChild(name);

                Element surname = doc.createElement("surname");
                surname.appendChild(doc.createTextNode(String.valueOf(userList.get(i).getSureName())));
                details.appendChild(surname);

                Element email = doc.createElement("email");
                email.appendChild(doc.createTextNode(String.valueOf(userList.get(i).geteMail())));
                details.appendChild(email);

                Element tel = doc.createElement("phone");
                tel.appendChild(doc.createTextNode(String.valueOf(userList.get(i).getTel())));
                details.appendChild(tel);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            try {
                Transformer aTransformer = transformerFactory.newTransformer();
                aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
                aTransformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source =  new DOMSource(doc);
                try {
                    FileWriter fos = new FileWriter("userList.xml");
                    StreamResult result = new StreamResult(fos);
                    try {
                        aTransformer.transform(source, result);
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }


}

