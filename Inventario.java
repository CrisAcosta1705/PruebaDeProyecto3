package Proyecto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private static List<Producto> productos;
    

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("==== MENÚ DEL INVENTARIO ====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Informe del inventario");
            System.out.println("4. Realizar venta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        registrarProducto();
                        break;
                    case 2:
                        mostrarInventario();
                        break;
                    case 3:
                        generarInformeInventario();
                    case 4:
                    	realizarVenta();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido. Inténtelo nuevamente.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

    public void registrarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese las unidades del producto: ");
        int unidades = scanner.nextInt();
        scanner.nextLine();

        Producto producto = new Producto(marca, precio, unidades);
        agregarProducto(producto);
        System.out.println("Producto registrado exitosamente.");
    }

    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("==== INVENTARIO ====");
            System.out.println("Marca\t\tPrecio\t\tUnidades");
            System.out.println("----------------------------------------");
            for (Producto producto : productos) {
                System.out.printf("%-16s%-16.2f%-16d\n", producto.GetMarca(), producto.GetPrecio(), producto.getUnidades());
            }
            System.out.println("----------------------------------------");
        }
    
    }

    public void generarInformeInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("==== INFORME DE INVENTARIO ====");
            int totalProductos = 0;
            double valorTotalInventario = 0.0;

            for (Producto producto : productos) {
                int unidades = producto.getUnidades();
                double precio = producto.GetPrecio();
                double valorInventario = unidades * precio;

                System.out.println("Marca: " + producto.GetMarca());
                System.out.println("Unidades: " + unidades);
                System.out.println("Precio: $" + precio);
                System.out.println("Valor del inventario: $" + valorInventario);
                System.out.println("----------------------------------------");

                totalProductos += unidades;
                valorTotalInventario += valorInventario;
            }

            System.out.println("Total de productos en el inventario: " + totalProductos);
            System.out.println("Valor total del inventario: $" + valorTotalInventario);
            System.out.println("----------------------------------------");
        }
    }
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        inventario.mostrarMenu();
    }

    public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== REALIZAR VENTA ====");

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario para realizar la venta.");
            return;
        }

        System.out.print("Ingrese el nombre del producto: ");
        String marca = scanner.nextLine();

        for (Producto producto : productos) {

        if (producto != null) {
            System.out.print("Ingrese la cantidad a vender: ");
            int cantidad = scanner.nextInt();

            if (cantidad > 0 && cantidad <= producto.getUnidades()) {
     
                producto.setUnidades(producto.getUnidades() - cantidad);
                System.out.println("Venta realizada exitosamente.");
            } else {
                System.out.println("Cantidad inválida. La venta no se puede realizar.");
            }
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
        
        scanner.nextLine(); 
        
   
        mostrarMenu();
    }
}
}
	