import java.util.Scanner;

public class Ex1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Input your name");
        String name = sc.nextLine();

        System.out.println("Input your surname");
        String sureName = sc.nextLine();

        System.out.println("Hello, " + name + " " + sureName);


    }
}
