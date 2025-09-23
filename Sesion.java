public class Sesion {
    private Usuario usuarioActual;
    private boolean activa;

    public Sesion() {
        this.usuarioActual = null;
        this.activa = false;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
        this.activa = true;
        System.out.println("Sesión iniciada para: " + usuario.getNombre());
    }

    public void cerrarSesion() {
        if (activa) {
            System.out.println("sesion cerrada: " + usuarioActual.getNombre());
            this.usuarioActual = null;
            this.activa = false;
        } else {
            System.out.println("No hay sesion activa.");
        }
    }

    public boolean isActiva() {
        return activa;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}