import java.io.*;
import java.util.Scanner;

public class DiarioExploradores {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String archivo = "descubrimientos.txt";
        String nombre, descripcion, linea, busqueda;
        int contador = 0;
        boolean encontrado = false;

        try {
            // Ingresar un nuevo descubrimiento
            System.out.print("Ingrese el nombre del descubrimiento: ");
            nombre = sc.nextLine();

            System.out.print("Ingrese la descripcion: ");
            descripcion = sc.nextLine();

            // Guardar al final del archivo
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true));

            bw.write(nombre + ";" + descripcion);
            bw.newLine();
            bw.close();

            System.out.println("\nDescubrimiento guardado correctamente.");

            // Verificar si el archivo existe
            File fichero = new File(archivo);

            if (fichero.exists() && fichero.length() > 0) {

                // Leer los registros
                BufferedReader br = new BufferedReader(
                    new FileReader(archivo));

                System.out.println("\n--- DESCUBRIMIENTOS ---");

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    contador++;
                }

                br.close();

                System.out.println("\nTotal de descubrimientos: " + contador);

                // Buscar un descubrimiento
                System.out.print("\nIngrese el nombre a buscar: ");
                busqueda = sc.nextLine();

                br = new BufferedReader(new FileReader(archivo));

                while ((linea = br.readLine()) != null) {

                    String[] datos = linea.split(";", 2);

                    if (datos[0].equalsIgnoreCase(busqueda)) {
                        encontrado = true;
                        System.out.println("Encontrado: " + linea);
                    }
                }

                br.close();

                if (!encontrado) {
                    System.out.println("Descubrimiento no encontrado.");
                }

            } else {
                System.out.println("El archivo esta vacio.");
            }

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }

        sc.close();
    }
}
