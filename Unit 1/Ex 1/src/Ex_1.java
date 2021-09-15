import java.util.Scanner;

public class Ex_1 {
    public static void main(String[] args) {

        /*this program read the input of characters and save this in string, then count with letter is Upper and Lower
         in the table ASCII * and save the Upper letters count in the variable
         */
        String letters = "";
        int numLetters = 0;
        char minLetters = Character.MIN_VALUE;
        char maxLetters = Character.MAX_VALUE;
        int numUpperLetters = 0;

        boolean exit = false;


        while (numLetters < 10 && !exit) {
            //read one
            System.out.println("Introduce una letra. Pulsa 0 si quieres salir: ");
            Scanner sc = new Scanner(System.in);
            char auxLetter = sc.next().charAt(0);
            System.out.println(" ");
            System.out.println("-------");
            letters += auxLetter;

            if (letters.toCharArray()[numLetters] == '0')
                exit = true;

            //save Upper and Lower letters
            if (minLetters > auxLetter) {
                minLetters = auxLetter;
            }
            if (maxLetters < auxLetter) {
                maxLetters = auxLetter;
            }

            //increment the count
            numLetters++;
        }

        if (numLetters > 50)
            System.out.println("El cadena de caracteres esta llena");

        //for every char in string
        for (int i = 0; i < numLetters && letters.toCharArray()[i] != '0'; i++) {
            //if the letter is Upper
            if ((letters.toCharArray()[i] >= 'A') && (letters.toCharArray()[i] <= 'Z')) {
                //add count if is Upper
                numUpperLetters++;
            }
        }

        //Write the result
        System.out.println("el Char menor es : " + minLetters);

        System.out.println("el Char mayor es : " + maxLetters);
        System.out.println("Hay " + numUpperLetters + " letras mayusculas ");

    }

}


