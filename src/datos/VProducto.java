
package datos;

/**
 *
 * @author Edwin
 */
public class VProducto {
    
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private double precioCompra;
    private double precioVenta;
    private double iva;
    private double precioconIva;
    private String descripcion;
    private int stock;
    private String operatividad;

    public VProducto() {
    }

    public VProducto(int idCategoria, String nombre, double precioCompra, double precioVenta, double iva, double precioconIva, String descripcion, int stock, String operatividad) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.iva = iva;
        this.precioconIva = precioconIva;
        this.descripcion = descripcion;
        this.stock = stock;
        this.operatividad = operatividad;
    }

    public VProducto(int idProducto, int idCategoria, String nombre, double precioCompra, double precioVenta, double iva, double precioconIva, String descripcion, int stock, String operatividad) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.iva = iva;
        this.precioconIva=precioconIva;
        this.descripcion = descripcion;
        this.stock = stock;
        this.operatividad = operatividad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getOperatividad() {
        return operatividad;
    }

    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
    }

    public double getPrecioconIva() {
        return precioconIva;
    }

    public void setPrecioconIva(double precioconIva) {
        this.precioconIva = precioconIva;
    }
    
    
    
    
    
}
