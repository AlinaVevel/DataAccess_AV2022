import java.util.Scanner;

public class Ex3 {

    public static void main(String[] args){

        int day, month, year;
        Scanner sc = new Scanner(System.in);
        day = sc.nextInt();
        month = sc.nextInt();
        year = sc.nextInt();
        try{
            Data data = new Data(day,month,year);

        } catch (Exception e) {
            System.out.println("Invalid day or month");
        }

    }
}
