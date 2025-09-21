
import java.util.Scanner;

public class Main {
    
    public static void main (String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            GestorUsuarios gestorUsuarios = new GestorUsuarios();
            GestorTareas gestorTareas = new GestorTareas();

            int opcion;
            int contadorUsuarios = 1;
            int contadorTareas = 1;
            int usuarioLogueadoId = -1;

            do {
                System.out.println("\n===== GESTOR DE TAREAS =====");
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Crear tarea (requiere login)");
                System.out.println("4. Listar mis tareas");
                System.out.println("5. Actualizar tarea");
                System.out.println("6. Eliminar tarea");
                System.out.println("7. Listar todos los usuarios (admin)");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String password = sc.nextLine();

                        Usuario nuevoUsuario = new Usuario(contadorUsuarios++, nombre, email, password);
                        gestorUsuarios.registrarUsuario(nuevoUsuario);
                    }

                    case 2 -> {
                        System.out.print("Email: " );
                        String emailLogin = sc.nextLine();
                        System.out.print("Contraseña: "); 
                        String passLogin = sc.nextLine();

                        if(gestorUsuarios.login(emailLogin, passLogin)) {
                            Usuario u = gestorUsuarios.buscarPorEmail(emailLogin);
                            usuarioLogueadoId = u.getId();
                        } else {
                            usuarioLogueadoId = -1;    
                        }
                    }

                    case 3 -> {
                        if(usuarioLogueadoId == -1) {
                            System.out.println("Debes iniciar sesion para crear una tarea.");
                        } else {
                            System.out.print("Titulo de la tarea: ");
                            String titulo = sc.nextLine();
                            System.out.print("Descripcion de la tarea: ");
                            String descripcion = sc.nextLine();

                            Tarea nuevaTarea = new Tarea(contadorTareas++, titulo, descripcion, "Pendiente", usuarioLogueadoId);
                            gestorTareas.agregarTarea(nuevaTarea);
                        }
                    }

                    case 4 -> {
                        if (usuarioLogueadoId == -1) {
                            System.out.println("Debes iniciar sesion para ver tus tareas!!!");
                            
                        } else {
                            gestorTareas.listarTareasPorUsuario(usuarioLogueadoId);
                        }
                    }

                    case 5 -> {
                        System.out.print("ID de la tarea a actualizar: ");
                        int idActualizar = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nuevo titulo: ");
                        String nuevoTitulo = sc.nextLine();
                        System.out.print("Nueva descripcion: ");
                        String nuevaDescripcion = sc.nextLine();
                        System.out.print("Nuevo estado (Pendiente/En progreso/Completada): ");
                        String nuevoEstado = sc.nextLine();
                        
                        
                        gestorTareas.actualizarTarea(idActualizar, nuevoTitulo, nuevaDescripcion, nuevoEstado);
                    }

                    case 6 -> {
                        System.out.print("ID de la tarea a eliminar: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine();
                        gestorTareas.eliminarTarea(idEliminar);
                    }

                    case 7 -> gestorUsuarios.listarUsuarios();

                    case 0 -> System.out.println("👋 ¡Gracias por usar el Gestor de Tareas!");
                    
                    default -> System.out.println("❌ Opción inválida. Intenta de nuevo.");
                    
                }
            } while (opcion != 0);
        }
        }
    
}
