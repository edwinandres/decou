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
public class VMora {
    
    private int idMora;
    private int idCredito;
    private int numeroCuota;
    private String operatividad;
    private double totalcuota;
    private double totalabonado;
    private double adeuda;
    private Date fecha;

    public VMora() {
    }

    public VMora(int idMora, int idCredito, int numeroCuota) {
        this.idMora = idMora;
        this.idCredito = idCredito;
        this.numeroCuota = numeroCuota;
    }

    public VMora(int idCredito, int numeroCuota) {
        this.idCredito = idCredito;
        this.numeroCuota = numeroCuota;
    }

    public int getIdMora() {
        return idMora;
    }

    public void setIdMora(int idMora) {
        this.idMora = idMora;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getOperatividad() {
        return operatividad;
    }

    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
    }

    public double getTotalcuota() {
        return totalcuota;
    }

    public void setTotalcuota(double totalcuota) {
        this.totalcuota = totalcuota;
    }

    public double getTotalabonado() {
        return totalabonado;
    }

    public void setTotalabonado(double totalabonado) {
        this.totalabonado = totalabonado;
    }

    public double getAdeuda() {
        return adeuda;
    }

    public void setAdeuda(double adeuda) {
        this.adeuda = adeuda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
