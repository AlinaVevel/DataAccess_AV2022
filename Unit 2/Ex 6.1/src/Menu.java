import java.io.*;
import java.util.Scanner;

public class Menu implements Colors {

    ListOfUsers list = new ListOfUsers();
    Scanner scanner = new Scanner(System.in);

    public void lunchMenu() {
        System.out.println(ANSI_CYAN + "-------Menu---------" + ANSI_RESET);
        System.out.println("1. Add Users");
        System.out.println("2. Print all users");
        System.out.println("3. Search for User");

        System.out.println(ANSI_RED + "4. Exit" + ANSI_RESET);
        System.out.println("\033[H\033[2J");
    }

    public void choiceMenu() {

        readFromFile();
        String answer;
        do {
            lunchMenu();
            answer = scanner.nextLine();
            switch (answer) {
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



                default:
                    break;
            }
        } while (!answer.equals("4"));
        saveToFile();


    }

    public void saveToFile() {
        String answer = "userList.obj";
        File file = new File(answer);
        if (file.exists()) {
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
        String answer = "userList.obj";

        File file = new File(answer);

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            list = (ListOfUsers) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}


