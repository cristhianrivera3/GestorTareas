import java.util.ArrayList;
public class GestorTareas {

    private final ArrayList<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        System.out.println("Tarea agregada con exito: " + tarea.getTitulo());
    }

    public void listarTareasPorUsuario(int usuarioId) {
        boolean hayTareas = false;
        System.out.println("Tareas del usuario con ID: " + usuarioId);
        for (Tarea t : tareas) {
            if (t.getUsuarioId() == usuarioId) {
                t.mostrarInfo();
                hayTareas = true;
            }
        }
        if(!hayTareas) {
            System.out.println("¡No hay tareas registradas para este usuario!.");
        }
    }

    public void actualizarTarea(int id, String nuevoTitulo, String nuevaDescripcion, String nuevoEstado) {
        for (Tarea t : tareas ) {
            if (t.getId() == id) {
                t.setTitulo(nuevoTitulo);
                t.setDescripcion(nuevaDescripcion);
                t.setEstado(nuevoEstado);
                System.out.println("Tarea actualizada con exito.");
                return;
            }
        }
        System.out.println("¡No se encontro ninguna tarea con ese ID!");
    }

    public void eliminarTarea(int id) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == id) {
                tareas.remove(i);
                System.out.println("Tarea eliminada con exito.");
                return;
            }
        }
        System.out.println("No se encontro ninguna tarea con ese ID.");
    }
}