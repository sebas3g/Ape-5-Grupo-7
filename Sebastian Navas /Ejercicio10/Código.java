import java.io.*;
import java.util.Scanner;

public class SistemaVentas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String archivo = "ventas.txt";
        String producto, linea, productoMayor = "";
        double precio, total = 0;
        double mayorPrecio = 0;
        double limite;
        int cantidad;

        try {

            // Registrar nuevas ventas
            System.out.print("Ingrese la cantidad de ventas: ");
            cantidad = Integer.parseInt(sc.nextLine());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a cero.");
                sc.close();
                return;
            }

            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true));

            for (int i = 1; i <= cantidad; i++) {

                System.out.println("\nVenta " + i);

                System.out.print("Nombre del producto: ");
                producto = sc.nextLine();

                while (producto.trim().isEmpty() || producto.contains(",")) {
                    System.out.print("Nombre no valido. Ingrese nuevamente: ");
                    producto = sc.nextLine();
                }

                System.out.print("Precio: ");
                precio = Double.parseDouble(sc.nextLine());

                while (!Double.isFinite(precio) || precio <= 0) {
                    System.out.print("Precio invalido. Ingrese nuevamente: ");
                    precio = Double.parseDouble(sc.nextLine());
                }

                bw.write(producto + "," + precio);
                bw.newLine();
            }

            bw.close();

            System.out.println("\nVentas guardadas correctamente.");

            // Leer los registros
            BufferedReader br = new BufferedReader(
                new FileReader(archivo));

            System.out.println("\n--- REGISTRO DE VENTAS ---");

            while ((linea = br.readLine()) != null) {

                try {
                    String[] datos = linea.split(",", -1);

                    if (datos.length != 2 || datos[0].trim().isEmpty()) {
                        System.out.println("Registro invalido: " + linea);
                        continue;
                    }

                    producto = datos[0].trim();
                    precio = Double.parseDouble(datos[1].trim());

                    if (!Double.isFinite(precio) || precio <= 0) {
                        System.out.println("Precio invalido: " + linea);
                        continue;
                    }

                    System.out.println(producto + " - $" + precio);

                    total += precio;

                    if (precio > mayorPrecio) {
                        mayorPrecio = precio;
                        productoMayor = producto;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Precio no numerico: " + linea);
                }
            }

            br.close();

            // Mostrar resultados
            System.out.println("\nTotal de ventas: $" + total);
            System.out.println("Producto mas caro: " + productoMayor);
            System.out.println("Precio mas alto: $" + mayorPrecio);

            // Filtrar productos
            System.out.print("\nIngrese el valor para filtrar: ");
            limite = Double.parseDouble(sc.nextLine());

            br = new BufferedReader(new FileReader(archivo));

            System.out.println("\n--- PRODUCTOS QUE SUPERAN $" + limite + " ---");

            while ((linea = br.readLine()) != null) {

                try {
                    String[] datos = linea.split(",", -1);

                    if (datos.length != 2 || datos[0].trim().isEmpty()) {
                        continue;
                    }

                    producto = datos[0].trim();
                    precio = Double.parseDouble(datos[1].trim());

                    if (Double.isFinite(precio) && precio > limite) {
                        System.out.println(producto + " - $" + precio);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Registro omitido: " + linea);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar un numero valido.");

        }

        sc.close();
    }
}
