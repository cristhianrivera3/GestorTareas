package Gestores.gestorProductos;

import Modelo.Producto;
import java.util.ArrayList;

public class GestorProductos {
    private final ArrayList<Producto> productos;

    public GestorProductos() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("✅ Producto agregado: " + producto.getNombre());
    }

    public void listarProductosPorUsuario(int usuarioId) {
        boolean hay = false;
        System.out.println("\n📦 Productos del usuario ID: " + usuarioId);
        for (Producto p : productos) {
            if (p.getUsuarioId() == usuarioId) {
                p.mostrarInfo();
                hay = true;
            }
        }
        if (!hay) System.out.println("⚠️ No hay productos registrados para este usuario.");
    }

    public void actualizarProducto(int id, String nuevoNombre, int nuevoStock, double nuevoPrecio) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setNombre(nuevoNombre);
                p.setStock(nuevoStock);
                p.setPrecio(nuevoPrecio);
                System.out.println("✅ Producto actualizado.");
                return;
            }
        }
        System.out.println("❌ No se encontró producto con ID: " + id);
    }

    public void eliminarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                System.out.println("✅ Producto eliminado.");
                return;
            }
        }
        System.out.println("❌ No se encontró producto con ID: " + id);
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }
    public ArrayList<Producto> obtenerProductosPorUsuario(int usuarioId) {
    ArrayList<Producto> lista = new ArrayList<>();
    for (Producto p : productos) {
        if (p.getUsuarioId() == usuarioId) {
            lista.add(p);
        }
    }
    return lista;
}
}
