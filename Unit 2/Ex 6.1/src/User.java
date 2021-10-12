import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable,Colors {

    private static final long serialVersionUID = -835882900113176646L;
    protected String name;
    protected String sureName;
    protected String eMail;
    protected String tel;


    public String getName() {
        return name;
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "Input your name " + ANSI_RESET);
        name = scanner.nextLine();
        while (name.isEmpty()) {
            System.out.println(ANSI_RED + "This field cant be empty"+ ANSI_RESET);
            name = scanner.nextLine();
        }

    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "Input your surname " + ANSI_RESET);
        sureName = scanner.nextLine();
        while (sureName.isEmpty()) {
            System.out.println(ANSI_RED + "This field cant be empty" + ANSI_RESET);
            sureName = scanner.nextLine();
        }
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "Input e-mail " + ANSI_RESET);
        eMail = scanner.nextLine();
        while (eMail.isEmpty()) {
            System.out.println(ANSI_RED + "This field cant be empty" + ANSI_RESET);
            eMail = scanner.nextLine();
        }
    }

    public void printInfoUser() {

        if (tel != null && eMail != null) {
            System.out.println(ANSI_CYAN + "The name is " + ANSI_RESET + name);
            System.out.println(ANSI_CYAN + "The surname is " + ANSI_RESET + sureName);
            System.out.println(ANSI_CYAN + "Phone number is " + ANSI_RESET + tel);
            System.out.println(ANSI_CYAN + "E-mail is " + ANSI_RESET + eMail);
        } else {
            System.out.println(ANSI_CYAN + "The name is " + ANSI_RESET + name);
            System.out.println(ANSI_CYAN + "The surname is " + ANSI_RESET + sureName);
        }

    }

    public String getTel() {
        return tel;
    }

    public void setTel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_YELLOW + "Input tel " + ANSI_RESET);
        tel = scanner.nextLine();
        while (tel.isEmpty()) {
            System.out.println(ANSI_RED + "This field cant be empty" + ANSI_RESET);
            tel = scanner.nextLine();
        }
    }

}
