import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {

    private static final String RUTA_ARCHIVO = "secreto.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE MENSAJES SECRETOS ===");
            System.out.println("1. Escribir nuevo mensaje secreto");
            System.out.println("2. Leer mensajes y contar lineas");
            System.out.println("3. Salir");
            System.out.print("Elija una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un numero valido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar salto de linea

            switch (opcion) {
                case 1:
                    System.out.print("Escriba el mensaje secreto: ");
                    String mensaje = scanner.nextLine();
                    escribirMensaje(mensaje);
                    break;
                case 2:
                    leerMensajes();
                    break;
                case 3:
                    System.out.println("Cerrando canal seguro...");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    public static void escribirMensaje(String mensaje) {
        try (FileWriter fw = new FileWriter(RUTA_ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(mensaje);
            bw.newLine();
            System.out.println("Mensaje guardado de forma segura en '" + RUTA_ARCHIVO + "'.");

        } catch (IOException e) {
            System.out.println("Error de E/S al guardar el mensaje: " + e.getMessage());
        }
    }

    public static void leerMensajes() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("Aun no existen mensajes registrados ('" + RUTA_ARCHIVO + "' no encontrado).");
            return;
        }

        int totalLineas = 0;
        System.out.println("\n--- Contenido de " + RUTA_ARCHIVO + " ---");

        try (FileReader fr = new FileReader(archivo);
             BufferedReader br = new BufferedReader(fr)) {

            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                System.out.println("[Linea " + totalLineas + "]: " + linea);
            }

            System.out.println("----------------------------------");
            System.out.println("Total de lineas registradas: " + totalLineas);

        } catch (IOException e) {
            System.out.println("Error de E/S al leer los mensajes: " + e.getMessage());
        }
    }
}
