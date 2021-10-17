package agregar_datos_persona;

public class Persona {

    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public Persona(
            String nombre,
            String apellido,
            int edad,
            String sexo
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(!nombre.isEmpty()) {
            this.apellido = apellido;
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if(edad > 0) {
            this.edad = edad;
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        if(!sexo.isEmpty()) {
            this.sexo = sexo;
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\tApellido: " + getApellido() +
                "\nEdad: " + getEdad() + "\tSexo: " + getSexo();
    }
}
