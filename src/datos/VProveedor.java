/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Edwin
 */
public class VProveedor extends VPersona{
    
    private int idProveedor;    
    private String nombreEmpresa;

    public VProveedor() {
    }

    public VProveedor(String nombreEmpresa, String numeroDocumento, String apellidos, String nombre, String telefono, String tipoPersona, String direccion, String correo, String operatividad) {
        super(numeroDocumento, apellidos, nombre, telefono, tipoPersona, direccion, correo, operatividad);
        this.nombreEmpresa = nombreEmpresa;
    }

    public VProveedor(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    
}
