import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio7 {

    static final String ARCHIVO = "jugadores.txt";

    // Clase que representa a un jugador
    static class Jugador {
        private String nombre;
        private double puntuacion;

        public Jugador(String nombre, double puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }

        public String getNombre() { return nombre; }
        public double getPuntuacion() { return puntuacion; }

        @Override
        public String toString() {
            return nombre + " - " + puntuacion;
        }
    }

    // Crea un archivo de ejemplo si no existe
    static void crearArchivoEjemplo() {
        File f = new File(ARCHIVO);
        if (f.exists()) return;
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println("Ana,85");
            pw.println("Luis,62");
            pw.println("Carla,91.5");
            pw.println("Pedro,abc");      // puntuación no numérica
            pw.println("Sofia 78");       // sin coma
            pw.println(",70");            // nombre vacío
            pw.println("Diego,74");
            pw.println("Marta,-5");       // puntuación negativa
            System.out.println("Se creo el archivo de ejemplo " + ARCHIVO + "\n");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    // Lee y valida los registros del archivo
    static List<Jugador> leerJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length != 2) {
                    System.out.println("Linea " + numLinea + " invalida (formato incorrecto): " + linea);
                    continue;
                }
                String nombre = partes[0].trim();
                if (nombre.isEmpty()) {
                    System.out.println("Linea " + numLinea + " invalida (nombre vacio): " + linea);
                    continue;
                }
                try {
                    double puntuacion = Double.parseDouble(partes[1].trim());
                    if (puntuacion < 0) {
                        System.out.println("Linea " + numLinea + " invalida (puntuacion negativa): " + linea);
                        continue;
                    }
                    jugadores.add(new Jugador(nombre, puntuacion));
                } catch (NumberFormatException e) {
                    System.out.println("Linea " + numLinea + " invalida (puntuacion no numerica): " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo " + ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return jugadores;
    }

    public static void main(String[] args) {
        crearArchivoEjemplo();
        List<Jugador> jugadores = leerJugadores();

        if (jugadores.isEmpty()) {
            System.out.println("\nNo hay registros validos para procesar.");
            return;
        }

        double suma = 0;
        Jugador mejor = jugadores.get(0);
        for (Jugador j : jugadores) {
            suma += j.getPuntuacion();
            if (j.getPuntuacion() > mejor.getPuntuacion()) {
                mejor = j;
            }
        }
        double promedio = suma / jugadores.size();

        System.out.println("\n===== RESULTADOS =====");
        System.out.println("Registros validos: " + jugadores.size());
        System.out.printf("Promedio de puntuaciones: %.2f%n", promedio);
        System.out.println("Jugador con mayor puntaje: " + mejor);

        System.out.println("\nJugadores con puntuacion superior a 70:");
        for (Jugador j : jugadores) {
            if (j.getPuntuacion() > 70) {
                System.out.println("  " + j);
            }
        }
    }
}
