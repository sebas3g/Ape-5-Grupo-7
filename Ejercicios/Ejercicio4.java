import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4 {

    public static void main(String[] args) {
        String rutaArchivo = "resultados.txt";
        int puntajeTotal = 0;
        int puntajeMaximo = Integer.MIN_VALUE;
        String mejorParticipante = "";
        boolean hayDatos = false;

        System.out.println("=== RESULTADOS DEL PARQUE DE AVENTURAS ===");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue; // Ignora líneas en blanco
                }

                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int puntaje = Integer.parseInt(partes[1].trim());

                    // Mostrar participante
                    System.out.println("Participante: " + nombre + " | Puntaje: " + puntaje);

                    // Acumular total
                    puntajeTotal += puntaje;

                    // Verificar puntaje máximo
                    if (puntaje > puntajeMaximo) {
                        puntajeMaximo = puntaje;
                        mejorParticipante = nombre;
                    }

                    hayDatos = true;
                }
            }

            System.out.println("------------------------------------------");
            if (hayDatos) {
                System.out.println("Puntaje total acumulado: " + puntajeTotal);
                System.out.println("Puntaje mas alto: " + puntajeMaximo + " (" + mejorParticipante + ")");
            } else {
                System.out.println("El archivo no contiene registros validos.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + rutaArchivo + "': " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: uno de los puntajes no es un número entero valido.");
        }
    }
}
