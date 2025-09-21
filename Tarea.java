


public class Tarea {

    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private int usuarioId;


    public Tarea(int id, String titulo, String descripcion, String estado, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    } 

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;  
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    } 
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }


    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Titulo: " + titulo + " | Estado: " + estado);
    }


}