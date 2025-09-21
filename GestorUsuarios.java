

import java.util.ArrayList;
public class GestorUsuarios {
    private final ArrayList<Usuario> usuarios;
    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println(" Usuario registrado con exito :) : " + usuario.getNombre());
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if(u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public boolean login(String email, String password) {
        Usuario u = buscarPorEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            System.out.println(" Login exitoso. Bienvenid@ " + u.getNombre());
            return true;
        }
        System.out.println(" Email o contraseña incorrectos :( ");
        return false;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No existen usuario registrados");
        } else {
            System.out.println("Lista de usuarios");
            for (Usuario u : usuarios) {
                u.mostrarInfo();
            }
        }
    }

}