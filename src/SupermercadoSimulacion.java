import java.util.ArrayList;
import java.util.List;

public class SupermercadoSimulacion {

    public static void main(String[] args) {
        // Crear lista de productos
        List<Producto> productos = new ArrayList<>();

        // Agregar productos a la lista con nombre, precio y cantidad
        productos.add(new Producto("Leche", 2.50, 2));
        productos.add(new Producto("Pan", 1.00, 3));
        productos.add(new Producto("Manzanas", 3.00, 5));

        // Calcular el total de la compra
        double totalCompra = 0;
        for (Producto producto : productos) {
            totalCompra += producto.getCostoTotal(); // Usamos getCostoTotal() para obtener el total de cada producto
        }

        // Imprimir los productos y su costo total
        System.out.println("Productos comprados:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - Precio: $" + producto.getPrecio() + " - Cantidad: " + producto.getCantidad());
        }

        System.out.println("\nTotal de la compra: $" + totalCompra);
    }
}
