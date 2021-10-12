import java.io.Serializable;
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
}

