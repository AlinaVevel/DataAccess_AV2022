import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Menu implements Colors {

    ListOfUsers list = new ListOfUsers();
    Scanner scanner = new Scanner(System.in);


    public void lunchMenu() {
        System.out.println(ANSI_CYAN + "-------Menu---------" + ANSI_RESET);
        System.out.println("0. Read from existing file");
        System.out.println("1. Add Users");
        System.out.println("2. Print all users");
        System.out.println("3. Search for User");
        System.out.println("4. Save to xml file");
        System.out.println("5. Read from XML file");
        System.out.println("6. Save to obj file");
        System.out.println(ANSI_RED + "7. Exit" + ANSI_RESET);
        System.out.println("\033[H\033[2J");
    }

    public void choiceMenu() {


        String answer;
        do {

            lunchMenu();
            answer = scanner.nextLine();
            switch (answer) {
                case "0":
                    readFromFile();
                    break;
                case "1":
                    list.addUsers();
                    System.out.println("\033[H\033[2J");
                    break;
                case "2":
                    list.allInfo();
                    System.out.println("\033[H\033[2J");
                    break;
                case "3":
                    list.searchForUser();
                    System.out.println("\033[H\033[2J");
                    break;

                case "4"  :
                    list.writeXMLFile();
                    break;
                case "5" :
                    MyRunner runner = new MyRunner();
                    List<User> lines = runner.parseUsers();

                    for (int i = 0; i < lines.size(); i++) {
                        lines.get(i).printInfoUser();
                    }
                    break;
                case "6":
                    saveToFile();
                    break;


                default:
                    break;
            }
        } while (!answer.equals("7"));



    }

    public void saveToFile() {
        System.out.println("Input name of obj");

        String answer = scanner.nextLine();
        File file = new File(answer);
        if (!file.exists()) {
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(list);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void readFromFile() {
            String answer = scanner.nextLine();

            File file = new File(answer);
            if(!file.exists()){
                System.out.println("File dont exist");
                answer = scanner.nextLine();
                file = new File(answer);
            }

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            list = (ListOfUsers) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }






}
