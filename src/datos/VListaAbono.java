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
public class VListaAbono {
    private int idCredito;
    private int idAbono;
    private double valorPagado;
    private Date fecha;

    
   

    public VListaAbono() {
    }

    public VListaAbono(int idCredito, int idAbono, double valorPagado,Date fecha) {
        this.idCredito = idCredito;
        this.idAbono = idAbono;
        this.valorPagado = valorPagado;
        this.fecha=fecha;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
