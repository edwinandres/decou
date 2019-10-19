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
public class VListaFactura {
    
    private int codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioArticulo;
    private double iva;
    private double precioVenta;
    private double totalPorArticulo;

    public VListaFactura() {
    }

    public VListaFactura(int codigoArticulo, String nombreArticulo, int cantidad, double precioArticulo, double iva, double precioVenta, double totalPorArticulo) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioArticulo = precioArticulo;
        this.iva = iva;
        this.precioVenta = precioVenta;
        this.totalPorArticulo = totalPorArticulo;
    }

    public VListaFactura(int cod, String nom, int cant, double pventa, double iva, double tot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(double precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getTotalPorArticulo() {
        return totalPorArticulo;
    }

    public void setTotalPorArticulo(double totalPorArticulo) {
        this.totalPorArticulo = totalPorArticulo;
    }
    
    
    
}
