
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {

    public static void main(String[] args) {
        String archivo = "mensaje.txt";
        int totalCaracteres = 0;

        try (FileReader fr = new FileReader(archivo)) {
            int codigo; // read() devuelve un int, no un char

            System.out.println("=== MENSAJE RECIBIDO ===");

            // read() devuelve -1 cuando llega al final del archivo
            while ((codigo = fr.read()) != -1) {
                char caracter = (char) codigo; // convertimos el codigo a caracter
                System.out.print(caracter);
                totalCaracteres++;
            }

            System.out.println("========================");
            System.out.println("Total de caracteres: " + totalCaracteres);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
