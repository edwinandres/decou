
package datos;

/**
 *
 * @author Edwin
 */
public class VPersona {
    
    private int codigoPersona;    
    private String numeroDocumento;
    private String apellidos;
    private String nombre;
    private String telefono;
    private String tipoPersona;
    private String direccion;
    private String correo;
    private String operatividad;

    public VPersona() {
    }

    public VPersona(String numeroDocumento, String apellidos, String nombre, String telefono, String tipoPersona, String direccion, String correo, String operatividad) {
        this.numeroDocumento = numeroDocumento;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoPersona = tipoPersona;
        this.direccion = direccion;
        this.correo = correo;
        this.operatividad = operatividad;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOperatividad() {
        return operatividad;
    }

    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
    }
    
    
    
    
    
}
