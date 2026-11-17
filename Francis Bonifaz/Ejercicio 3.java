import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ejercicio3 {

    public static void main(String[] args) {
        String archivo = "obras.txt";

        // Guarda cada categoria con su cantidad de obras
        Map<String, Integer> categorias = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            System.out.println("=== OBRAS DEL MUSEO ===");

            while ((linea = br.readLine()) != null) {
                // separamos el nombre y la categoria por la coma
                String[] partes = linea.split(",");

                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String categoria = partes[1].trim();

                    System.out.println("Obra: " + nombre + " | Categoria: " + categoria);

                    // si la categoria no existe empieza en 0, luego suma 1
                    categorias.put(categoria, categorias.getOrDefault(categoria, 0) + 1);
                }
            }

            System.out.println("\n=== CLASIFICACION POR CATEGORIA ===");
            for (Map.Entry<String, Integer> entrada : categorias.entrySet()) {
                System.out.println(entrada.getKey() + ": " + entrada.getValue() + " obra(s)");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
