import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Ex3_5_2 {

    public static void main(String[] args) {


        try {
            InputStream foto = new FileInputStream("dogCompressed.bmp");
            byte[] cadenaFoto = new byte[31];
            foto.read(cadenaFoto);

            int wight = Byte.toUnsignedInt(cadenaFoto[18]) + (Byte.toUnsignedInt(cadenaFoto[19]) * 256) + (Byte.toUnsignedInt(cadenaFoto[20]) * 256 * 256) + (Byte.toUnsignedInt(cadenaFoto[21]) * 256 * 256 * 256);
            int height = Byte.toUnsignedInt(cadenaFoto[22]) + (Byte.toUnsignedInt(cadenaFoto[23]) * 256) + (Byte.toUnsignedInt(cadenaFoto[24]) * 256 * 256) + (Byte.toUnsignedInt(cadenaFoto[25]) * 256 * 256 * 256);

            System.out.println("Widght is " + wight + " pixels");
            System.out.println("Height is " + height + " pixels");
            if(Byte.toUnsignedInt(cadenaFoto[30]) == 0){
                System.out.println("Image is not compressed");
            }
            else{
                System.out.println("Is compressed");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
