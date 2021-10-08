import java.io.*;
import java.util.Scanner;

public class Ex4_4_4 {

    public static void main(String[] args) {

        System.out.println("Input your file");
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();
        String answer;

        BufferedReader in = null;


        try {


            File fileCheck = new File(file);

            while(!fileCheck.exists()) {
                System.out.println("File dont exist");
                fileCheck = new File(scanner.nextLine());
            }

            in = new BufferedReader(new FileReader(fileCheck));
            System.out.println("Input string for search");
            answer = scanner.nextLine();


            int count = 0;
            String line = in.readLine();
            while( line != null) {



                if( line.equals(answer)){

                    System.out.println("line number " + count  + " " + line);

                }
                count++;
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
