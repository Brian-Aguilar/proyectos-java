package negocios_database_mvc.model;

public class Cliente {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    public Cliente() {}
    public Cliente(
            String nombre,
            String direccion,
            String telefono,
            String correo
    ) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }
    public Cliente(
            int id,
            String nombre,
            String direccion,
            String telefono,
            String correo
    ) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Datos: " + getId() + " - " + getNombre() + " - " + getCorreo() + " - " + getTelefono() + " - " + getDireccion();
    }
}
