package Modelo; 

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precio;
    private int usuarioId;

    public Producto(int id, String nombre, int stock, double precio, int usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.usuarioId = usuarioId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public void mostrarInfo() {
        System.out.printf("ID: %d | %s | Stock: %d | Precio: $%.2f%n", id, nombre, stock, precio);
    }
}