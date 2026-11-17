import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio8 {

    static final String ARCHIVO = "temperaturas.txt";
    static Scanner sc = new Scanner(System.in);

    // Guarda una nueva temperatura al final del archivo (modo append)
    static void guardarTemperatura() {
        System.out.print("Ingrese la temperatura (°C): ");
        String entrada = sc.nextLine().trim();
        try {
            double temp = Double.parseDouble(entrada);
            try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
                pw.println(temp);
                System.out.println("Temperatura guardada correctamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: '" + entrada + "' no es un valor numerico valido.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Lee el archivo y convierte cada línea a número, omitiendo las invalidas
    static List<Double> leerTemperaturas() {
        List<Double> temperaturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;
                try {
                    temperaturas.add(Double.parseDouble(linea));
                } catch (NumberFormatException e) {
                    System.out.println("Linea " + numLinea + " ignorada (formato no numerico): " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + ARCHIVO + " no existe todavia. Guarde temperaturas primero.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return temperaturas;
    }

    // Muestra los registros y las estadísticas
    static void mostrarEstadisticas() {
        List<Double> temps = leerTemperaturas();
        if (temps.isEmpty()) {
            System.out.println("No hay registros validos para procesar.");
            return;
        }

        double suma = 0;
        double maxima = temps.get(0);
        int mayores30 = 0;

        System.out.println("\nRegistros validos:");
        for (int i = 0; i < temps.size(); i++) {
            double t = temps.get(i);
            System.out.println("  " + (i + 1) + ". " + t + " °C");
            suma += t;
            if (t > maxima) maxima = t;
            if (t > 30) mayores30++;
        }

        System.out.println("\n===== RESULTADOS =====");
        System.out.printf("Temperatura promedio: %.2f °C%n", suma / temps.size());
        System.out.println("Temperatura mas alta: " + maxima + " °C");
        System.out.println("Registros que superan los 30 °C: " + mayores30);
    }

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n===== CONTROL DE TEMPERATURA =====");
            System.out.println("1. Guardar nueva temperatura");
            System.out.println("2. Leer registros y mostrar estadisticas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida. Ingrese un numero del 1 al 3.");
                continue;
            }
            switch (opcion) {
                case 1: guardarTemperatura(); break;
                case 2: mostrarEstadisticas(); break;
                case 3: System.out.println("Programa finalizado."); break;
                default: System.out.println("Opcion invalida. Ingrese un numero del 1 al 3.");
            }
        } while (opcion != 3);
    }
}
