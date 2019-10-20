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
public class VCredito {
    
    private int idCredito;
    private int numFactura;
    private int numeroCuotas;
    private double valorCuota;
    private String estado;
    private String referencia1;
    private String referencia2;
    private String caducidad;
    private String consideraciones;

    public VCredito() {
    }

    public VCredito(int numFactura, int numeroCuotas, double valorCuota, String estado, String referencia1, String referencia2, String caducidad, String consideraciones) {
        this.numFactura = numFactura;
        this.numeroCuotas = numeroCuotas;
        this.valorCuota = valorCuota;
        this.estado = estado;
        this.referencia1 = referencia1;
        this.referencia2 = referencia2;
        this.caducidad = caducidad;
        this.consideraciones = consideraciones;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia1() {
        return referencia1;
    }

    public void setReferencia1(String referencia1) {
        this.referencia1 = referencia1;
    }

    public String getReferencia2() {
        return referencia2;
    }

    public void setReferencia2(String referencia2) {
        this.referencia2 = referencia2;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getConsideraciones() {
        return consideraciones;
    }

    public void setConsideraciones(String consideraciones) {
        this.consideraciones = consideraciones;
    }
    
    
    
    
}
