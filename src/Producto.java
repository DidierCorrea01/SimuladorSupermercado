public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private long tiempoProcesamiento; // Tiempo de procesamiento en milisegundos

    // Constructor
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        // Simulación de tiempo de procesamiento (en milisegundos)
        this.tiempoProcesamiento = (long) (Math.random() * 1000); // Tiempo aleatorio entre 0 y 1000 ms
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public long getTiempoProcesamiento() {
        return tiempoProcesamiento;
    }

    // Método para calcular el costo total de un producto (precio * cantidad)
    public double getCostoTotal() {
        return precio * cantidad;
    }
}
