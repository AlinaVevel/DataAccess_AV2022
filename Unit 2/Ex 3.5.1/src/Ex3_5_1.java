import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ex3_5_1 {
    public static void main(String[] args) {
        try {
            InputStream foto = new FileInputStream("dogGif.gif");
            byte[] cadenaFoto = new byte[6];
            foto.read(cadenaFoto);

            if(Byte.toUnsignedInt(cadenaFoto[0]) == 0xFF && Byte.toUnsignedInt(cadenaFoto[1]) == 0xD8 && Byte.toUnsignedInt(cadenaFoto[2]) == 0xFF){
                System.out.println("JPG");
            }
            else if(Byte.toUnsignedInt(cadenaFoto[0]) == 0x42 && Byte.toUnsignedInt(cadenaFoto[1]) == 0x4D){
                System.out.println("BMP");
            }

            else if((Byte.toUnsignedInt(cadenaFoto[0]) == 0x47 && Byte.toUnsignedInt(cadenaFoto[1]) == 0x49 && Byte.toUnsignedInt(cadenaFoto[2]) == 0x46 && Byte.toUnsignedInt(cadenaFoto[3]) == 0x38) && Byte.toUnsignedInt(cadenaFoto[4]) == 0x39 && Byte.toUnsignedInt(cadenaFoto[5]) == 0x61 || Byte.toUnsignedInt(cadenaFoto[4]) == 0x37 && Byte.toUnsignedInt(cadenaFoto[5]) == 0x61){
                System.out.println("GIF");
            }

            else if(Byte.toUnsignedInt(cadenaFoto[0]) == 0x00 && Byte.toUnsignedInt(cadenaFoto[1]) == 0x00 && Byte.toUnsignedInt(cadenaFoto[2]) == 0x01 && Byte.toUnsignedInt(cadenaFoto[3]) == 0x00 ){
                System.out.println("ICO");
            }

            else if(Byte.toUnsignedInt(cadenaFoto[0]) == 0x89 && Byte.toUnsignedInt(cadenaFoto[1]) == 0x50 && Byte.toUnsignedInt(cadenaFoto[2]) == 0x4E && Byte.toUnsignedInt(cadenaFoto[3]) == 0x47 ){
                System.out.println("PNG");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

