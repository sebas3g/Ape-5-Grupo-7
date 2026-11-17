import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) {
        String archivo = "pistas.txt";
        int contador = 0;

        // try-with-resources: cierra el archivo solo al terminar
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            System.out.println("=== PISTAS DE LA INVESTIGACION ===");

            // readLine() devuelve null cuando ya no hay mas lineas
            while ((linea = br.readLine()) != null) {
                contador++;
                System.out.println("Pista " + contador + ": " + linea);
            }

            System.out.println("----------------------------------");
            System.out.println("Total de pistas: " + contador);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
