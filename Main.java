import Gestores.gestorProductos.GestorProductos;
import Gestores.gestorUsuario.GestorUsuario;
import Modelo.Producto;
import Modelo.usuario.Usuario;
import Utils.Validador.Validador;
import java.util.Scanner;
import vista.loginRegister.LoginRegisterGUI.LoginRegisterGUI;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            GestorUsuario gestorUsuarios = new GestorUsuario();
            GestorProductos gestorProductos = new GestorProductos();

            int contadorUsuarios = 1;
            int contadorProductos = 1;
            int usuarioLogueadoId = -1;
            int opcion;

            LoginRegisterGUI loginGUI = new LoginRegisterGUI(gestorUsuarios, gestorProductos, contadorUsuarios);
            loginGUI.setVisible(true);

            do {
                System.out.println("\n===== GESTOR DE INVENTARIO =====");
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Agregar producto (requiere login)");
                System.out.println("4. Listar mis productos");
                System.out.println("5. Actualizar producto");
                System.out.println("6. Eliminar producto");
                System.out.println("7. Listar todos los usuarios (admin)");
                System.out.println("0. Salir");
                System.out.print("Elige: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String pass = sc.nextLine();
                        if (!Validador.textoNoVacio(nombre) || !Validador.emailValido(email) || !Validador.textoNoVacio(pass)) {
                            System.out.println("❌ Datos inválidos.");
                            break;
                        }
                        Usuario nuevo = new Usuario(contadorUsuarios++, nombre, email, pass);
                        gestorUsuarios.registrarUsuario(nuevo);
                    }
                    case 2 -> {
                        System.out.print("Email: ");
                        String emailLogin = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String passLogin = sc.nextLine();
                        if (gestorUsuarios.login(emailLogin, passLogin)) {
                            Usuario u = gestorUsuarios.buscarPorEmail(emailLogin);
                            usuarioLogueadoId = u.getId();
                        } else {
                            usuarioLogueadoId = -1;
                        }
                    }
                    case 3 -> {
                        if (usuarioLogueadoId == -1) {
                            System.out.println("❌ Debes iniciar sesión.");
                        } else {
                            System.out.print("Nombre del producto: ");
                            String nombreProd = sc.nextLine();
                            System.out.print("Stock: ");
                            int stock = sc.nextInt();
                            System.out.print("Precio: ");
                            double precio = sc.nextDouble();
                            sc.nextLine();
                            if (!Validador.textoNoVacio(nombreProd) || !Validador.esEnteroPositivo(stock) || !Validador.precioValido(precio)) {
                                System.out.println("❌ Datos inválidos.");
                                break;
                            }
                            Producto p = new Producto(contadorProductos++, nombreProd, stock, precio, usuarioLogueadoId);
                            gestorProductos.agregarProducto(p);
                        }
                    }
                    case 4 -> {
                        if (usuarioLogueadoId == -1) {
                            System.out.println("❌ Inicia sesión.");
                        } else {
                            gestorProductos.listarProductosPorUsuario(usuarioLogueadoId);
                        }
                    }
                    case 5 -> {
                        System.out.print("ID del producto: ");
                        int idActualizar = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Nuevo stock: ");
                        int nuevoStock = sc.nextInt();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = sc.nextDouble();
                        sc.nextLine();
                        gestorProductos.actualizarProducto(idActualizar, nuevoNombre, nuevoStock, nuevoPrecio);
                    }
                    case 6 -> {
                        System.out.print("ID del producto: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine();
                        gestorProductos.eliminarProducto(idEliminar);
                    }
                    case 7 -> gestorUsuarios.listarUsuarios();
                    case 0 -> System.out.println("👋 ¡Gracias!");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } while (opcion != 0);

            sc.close();
        }
    }
}