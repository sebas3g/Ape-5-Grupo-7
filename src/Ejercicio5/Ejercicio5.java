import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {

    private static final String NOMBRE_ARCHIVO = "libros.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== BIBLIOTECA INTELIGENTE ===");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Leer titulos almacenados y contar libros");
            System.out.println("3. Buscar un libro especifico");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un numero valido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el titulo del libro a registrar: ");
                    String titulo = scanner.nextLine().trim();
                    if (!titulo.isEmpty()) {
                        registrarLibro(titulo);
                    } else {
                        System.out.println("El titulo no puede estar vacio.");
                    }
                    break;
                case 2:
                    leerYContarLibros();
                    break;
                case 3:
                    System.out.print("Ingrese el titulo (o palabra clave) a buscar: ");
                    String busqueda = scanner.nextLine().trim();
                    buscarLibro(busqueda);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema de biblioteca...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Agrega un libro al final del archivo sin borrar los anteriores
    public static void registrarLibro(String titulo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            bw.write(titulo);
            bw.newLine();
            System.out.println("Libro registrado correctamente en '" + NOMBRE_ARCHIVO + "'.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Valida existencia, muestra los titulos y cuenta el total
    public static void leerYContarLibros() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo '" + NOMBRE_ARCHIVO + "' aun no existe. Registre un libro primero.");
            return;
        }

        int contador = 0;
        System.out.println("\n--- Libros Registrados ---");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    contador++;
                    System.out.println(contador + ". " + linea);
                }
            }
            System.out.println("--------------------------");
            System.out.println("Total de libros registrados: " + contador);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Valida existencia y busca coincidencias sin importar mayusculas/minusculas
    public static void buscarLibro(String criterio) {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("El archivo '" + NOMBRE_ARCHIVO + "' no existe. No se puede realizar la busqueda.");
            return;
        }

        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (linea.toLowerCase().contains(criterio.toLowerCase())) {
                    System.out.println("Encontrado en registro #" + numeroLinea + ": " + linea);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron libros que coincidan con '" + criterio + "'.");
            }
        } catch (IOException e) {
            System.out.println("Error durante la busqueda: " + e.getMessage());
        }
    }
}
