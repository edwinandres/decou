/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Date;

/**
 *
 * @author Edwin
 */
public class VFactura {
    
    private int numeroFactura;
    private int idEmpleado;
    private double total;
    private Date fecha;
    private String cedulaCliente;
    private String tipoVenta;

    public VFactura() {
    }

    public VFactura(int idEmpleado, double total, Date fecha, String cedulaCliente, String tipoVenta) {
        this.idEmpleado = idEmpleado;
        this.total = total;
        this.fecha = fecha;
        this.cedulaCliente = cedulaCliente;
        this.tipoVenta = tipoVenta;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    
    
    
}
