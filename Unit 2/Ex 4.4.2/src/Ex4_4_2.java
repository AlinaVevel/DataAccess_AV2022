import java.io.*;
import java.util.*;

public class Ex4_4_2 {

    public static void main(String[] args) {

        BufferedReader in_one = null, in_two = null;
        PrintWriter out = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input first file");
        String first = scanner.nextLine();
        System.out.println("Input second file");
        String second = scanner.nextLine();

        try {
            in_one = new BufferedReader(new FileReader(first));
            in_two = new BufferedReader(new FileReader(second));
            out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));

            String string_one = in_one.readLine();
            String string_two = in_two.readLine();


            while (string_one != null && string_two != null) {


                if (string_one.compareTo(string_two) < 0) {
                    out.println(string_one);
                    string_one = in_one.readLine();

                } else {
                    out.println(string_two);
                    string_two = in_two.readLine();

                }


            }
            if (string_one == null) {
                while (string_two != null) {
                    out.write(string_two);
                    string_two = in_two.readLine();
                    out.println("");
                }

            }
            if (string_two == null) {
                while (string_one != null) {
                    out.write(string_one);
                    string_one = in_one.readLine();
                    out.println("");
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in_one.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();

        }

    }
}
