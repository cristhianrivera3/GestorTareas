public class Usuario {
    private int id;
    private String nombre ;
    private String email ;
    private String password ;
    private String rol;//nuevo atributo


    public Usuario(int id, String nombre, String email, String password) {

        this.id = id;
        this.nombre = nombre; 
        this.email = email; 
        this.password = password;
        this.rol = "normal";//por defecto
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public void cambiarPassword(String nuevaPassword) {
        this.password = nuevaPassword;
        System.out.println("Contraseña cambiada con exito:");
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Email: "+ email);
    }

}
