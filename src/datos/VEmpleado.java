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
public class VEmpleado extends VPersona{
    
    private int idEmpleado;
    private String login;
    private String cargo;
    private String password;

    public VEmpleado() {
    }

    public VEmpleado(String login, String cargo, String password) {
        this.login = login;
        this.cargo = cargo;
        this.password = password;
    }

    public VEmpleado(String login, String cargo, String numeroDocumento, String apellidos, String nombre, String telefono, String tipoPersona, String direccion, String correo, String operatividad, String password) {
        super(numeroDocumento, apellidos, nombre, telefono, tipoPersona, direccion, correo, operatividad);
        this.login = login;
        this.cargo = cargo;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
    
}
