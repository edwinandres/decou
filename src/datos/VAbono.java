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
public class VAbono {
    
    private int idAbono;
    private int idCredito;
    private Date fecha;
    private double valorAbono;
    private int numCuota;
    private String notaCobranza;
    private String operatividad;

    public VAbono() {
    }

    public VAbono(int idAbono, int idCredito, Date fecha, double valorAbono, int numCuota, String notaCobranza, String operatividad) {
        this.idAbono = idAbono;
        this.idCredito = idCredito;
        this.fecha = fecha;
        this.valorAbono = valorAbono;
        this.numCuota = numCuota;
        this.notaCobranza = notaCobranza;
        this.operatividad = operatividad;
    }

    public VAbono(int idCredito, Date fecha, double valorAbono, int numCuota, String notaCobranza,String operatividad) {
        this.idCredito = idCredito;
        this.fecha = fecha;
        this.valorAbono = valorAbono;
        this.numCuota = numCuota;
        this.notaCobranza = notaCobranza;
        this.operatividad = operatividad;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValorAbono() {
        return valorAbono;
    }

    public void setValorAbono(double valorAbono) {
        this.valorAbono = valorAbono;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }

    public String getNotaCobranza() {
        return notaCobranza;
    }

    public void setNotaCobranza(String notaCobranza) {
        this.notaCobranza = notaCobranza;
    }

    public String getOperatividad() {
        return operatividad;
    }

    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
    }
    
    
    
}
