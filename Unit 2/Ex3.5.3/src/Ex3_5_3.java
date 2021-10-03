import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;


public class Ex3_5_3 {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input your file");
            String archivo = sc.nextLine();
            String bytesInFile = new String( new FileInputStream(archivo).readAllBytes() );

            System.out.println("Input the word for searching");
            String inputString = sc.nextLine();

            int pos = 0;

            for(int i = 0; i < bytesInFile.length(); i++){

                if (pos == -1) {
                    break;
                }
                else
                {
                    pos = bytesInFile.indexOf(inputString, i);
                    i = i + pos;
                    System.out.println(pos);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
