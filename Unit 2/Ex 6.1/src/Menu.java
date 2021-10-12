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
        System.out.println("4. Save to odt file");
        System.out.println("5. Work with save list of users (Adding new users to existing users, searching, rinting info)");
        System.out.println(ANSI_RED + "6. Exit" + ANSI_RESET);
        System.out.println("\033[H\033[2J");
    }

    public void choiceMenu() {

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
                case "4":
                    saveToFile();
                    System.out.println("\033[H\033[2J");
                    break;
                case "5":
                    readFromFile();
                    System.out.println("\033[H\033[2J");
                    break;


                default:
                    break;
            }
        } while (!answer.equals("6"));


    }

    public void saveToFile() {
        System.out.println("Input file");
        String answer = scanner.nextLine();
        File file = new File(answer);
        if (file.exists()) {
            System.out.println("File already exist, do you want to rewrite it?" + ANSI_CYAN + " Y - yes, N - for create new file" + ANSI_RESET);
            System.out.println("\033[H\033[2J");
            answer = scanner.nextLine();
            answer.toLowerCase();
            if (answer.equals("y")) {
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(list);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(list);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(list);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFromFile() {
        System.out.println("Input file");
        String answer = scanner.nextLine();
        File file = new File(answer);
        while (!file.exists()) {
            System.out.println("File dont exist");
            answer = scanner.nextLine();
            file = new File(answer);
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            list = (ListOfUsers) in.readObject();
            System.out.println("Now you will be working with this file");
            choiceMenu();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}


