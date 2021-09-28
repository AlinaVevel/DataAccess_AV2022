import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input day");
        int day = Integer.parseInt(sc.nextLine());

        System.out.println("Input month");
        int month = Integer.parseInt(sc.nextLine());

        System.out.println("Input year");
        int year = Integer.parseInt(sc.nextLine());

        if(day > 31 || day < 1 || month < 1 || month > 12){
            System.out.println("You are livin in locilandia");
        } else{
            System.out.println(day + " " + month + " " + year);
        }


    }
}
