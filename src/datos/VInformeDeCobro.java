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
public class VInformeDeCobro {
    //fecha, nombre, numero de credito, numero de factura, cuotas pagadas, cuotas por pagar
    //total factura, total abonado, saldo restante, observaciones
    private Date fecha;
    private String nombre;
    private int numeroCredito;
    private int numeroFactura;
    private int numeroCuota;
    private int numeroCuotasFaltantes;
    private double totalFactura;
    private double totalAbonado;
    private double saldoRestante;
    private String observaciones;

    public VInformeDeCobro() {
    }

    public VInformeDeCobro(Date fecha, String nombre, int numeroCredito, int numeroFactura, int numeroCuota, int numeroCuotasFaltantes, double totalFactura, double totalAbonado, double saldoRestante, String observaciones) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.numeroCredito = numeroCredito;
        this.numeroFactura = numeroFactura;
        this.numeroCuota = numeroCuota;
        this.numeroCuotasFaltantes = numeroCuotasFaltantes;
        this.totalFactura = totalFactura;
        this.totalAbonado = totalAbonado;
        this.saldoRestante = saldoRestante;
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(int numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public int getNumeroCuotasFaltantes() {
        return numeroCuotasFaltantes;
    }

    public void setNumeroCuotasFaltantes(int numeroCuotasFaltantes) {
        this.numeroCuotasFaltantes = numeroCuotasFaltantes;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public double getTotalAbonado() {
        return totalAbonado;
    }

    public void setTotalAbonado(double totalAbonado) {
        this.totalAbonado = totalAbonado;
    }

    public double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "VInformeDeCobro{" + "fecha=" + fecha + ", nombre=" + nombre + ", numeroCredito=" + numeroCredito + ", numeroFactura=" + numeroFactura + ", numeroCuota=" + numeroCuota + ", numeroCuotasFaltantes=" + numeroCuotasFaltantes + ", totalFactura=" + totalFactura + ", totalAbonado=" + totalAbonado + ", saldoRestante=" + saldoRestante + ", observaciones=" + observaciones + '}';
    }
    
    
    
    
}
