import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Ex4_4_1 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String answer;
        System.out.println("Input name of the file .txt");

        String fileToRead = scanner.nextLine();

        File file = new File(fileToRead);

        String extention = getFileExtension(file);

        while (!extention.equals("txt")) {

            System.out.println("Only file with .txt");
            fileToRead = scanner.nextLine();
            file = new File(fileToRead);
            extention = getFileExtension(file);
        }

        if (file.exists()) {
            System.out.println("You have the file with that name, would you like to rewrite that or add lines to it? Y - yes for rewrite, N - for adding");
            answer = scanner.nextLine();
            answer.toLowerCase();
            while (true) {
                if (answer.equals("y") || answer.equals("n")) {
                    break;
                }
                System.out.println("ONLY Y OR N");
                answer = scanner.nextLine();
                answer.toLowerCase();
            }

            if (answer.equals("y")) {
                System.out.println("You are rewriting the file");
                inputLines(file, 1, false);
            } else {
                System.out.println("You are adding lines to the file");
                try {
                    long countLine = Files.lines(Paths.get(String.valueOf(file))).count();
                    int count = (int) countLine;
                    inputLines(file, count + 1, true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            inputLines(file, 1, false);
        }


    }

    public static void inputLines(File file, int count, boolean chose) {
        PrintWriter printwriter = null;
        Scanner scanner = new Scanner(System.in);
        try {
            printwriter = new PrintWriter(new BufferedWriter(new FileWriter(file, chose)));
            String inputLine;
            System.out.println("Input your lines, for terminate pres enter");
            inputLine = scanner.nextLine();


            while (!inputLine.isEmpty()) {
                printwriter.println(count + " " + inputLine);
                count++;
                inputLine = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printwriter.flush();
            printwriter.close();
        }

    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // if file conteins . (dot)
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // substring file ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // if not return ""
        else return "";
    }
}
