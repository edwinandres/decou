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
public class VListaCredito {
    private int idCredito;
    private double totalCredito;
    private String apellidos;
    private String nombres;

    public VListaCredito() {
    }

    public VListaCredito(int idCredito, double totalCredito, String apellidos, String nombres) {
        this.idCredito = idCredito;
        this.totalCredito = totalCredito;
        this.apellidos = apellidos;
        this.nombres = nombres;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    
    
}
