package Gestores.gestorUsuario;

import java.util.ArrayList;

import Modelo.usuario.Usuario;

public class GestorUsuario {
    private final ArrayList<Usuario> usuarios;

    public GestorUsuario() {
        usuarios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("✅ Usuario registrado: " + usuario.getNombre());
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) return u;
        }
        return null;
    }

    public boolean login(String email, String password) {
        Usuario u = buscarPorEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            System.out.println("✅ Login exitoso. Bienvenido " + u.getNombre());
            return true;
        }
        System.out.println("❌ Email o contraseña incorrectos.");
        return false;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados.");
        } else {
            System.out.println("\n👥 Lista de usuarios:");
            for (Usuario u : usuarios) {
                u.mostrarInfo();
            }
        }
    }

    // Obtener usuario por ID (útil para mostrar detalles)
    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}