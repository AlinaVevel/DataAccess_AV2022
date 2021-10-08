import java.io.*;
import java.util.Scanner;

public class Ex4_4_3 {

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


            int count = 0;
            String line = in.readLine();
            while( line != null) {
                System.out.println(line);
                line = in.readLine();

                count++;
                if( count == 23){
                    System.out.println("Press enter for read 23 lines more");
                    answer = scanner.nextLine();
                    while(!answer.isEmpty()){
                        System.out.println("ENTER");
                        answer = scanner.nextLine();
                    }
                    System.out.println(line);
                    line = in.readLine();

                    count = 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
