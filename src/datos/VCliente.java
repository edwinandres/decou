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
public class VCliente extends VPersona{
    
    private int idCliente;
    private String historial;
    private String sector;

    

    public VCliente() {
    }

    public VCliente(String historial, String numeroDocumento, String apellidos, String nombre, String telefono, String tipoPersona, String direccion, String correo,String operatividad,String sector) {
        super(numeroDocumento, apellidos, nombre, telefono, tipoPersona, direccion, correo, operatividad);
        this.historial = historial;
        this.sector = sector;
        
    }

    public VCliente(String historial) {
        this.historial = historial;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    
}
