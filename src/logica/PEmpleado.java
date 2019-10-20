/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.VEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edwin
 */
public class PEmpleado {
    
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public static String[]usuario = new String[3];
    
    
    public DefaultTableModel mostrar(String buscar) {
        //Este procedimiento muestra un listado de todos los clientes sobre una tabla
        
        /*Se llama una instancia del objeto tabla y se crean los arrays que llevaran los datos*/
        DefaultTableModel modelo;
        String[] titulos = {"CodigoP","Cedula", "Nombre", "Apellido"};
        String[] registro = new String[4];
        
        /*se crea la tabla y se le asignan los titulos*/
        totalregistros = 0;        
        modelo = new DefaultTableModel(null, titulos)
                {
                boolean [] canEdit= new boolean []{
                    false, false,false
                };
        
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex){   
                    return canEdit [columnIndex];
                }
            };
        
        /*cadena de consulta*/
        sSQL = "select p.codigoPersona, p.numeroDocumento, p.nombre, p.apellidos from tblPersona p"
                + " where numeroDocumento like '%" + buscar + "%' and p.tipoPersona='E' and p.operatividad='S' order by numeroDocumento desc";
        
        /*se llevan todos los valores encontrados al ResultSet*/
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            /*Cada ciclo llena una nueva fila  en el array de datos con los datos encotrados en la bd*/
            while (rs.next()) {
                registro[0] = rs.getString("codigoPersona");
                registro[1] = rs.getString("numeroDocumento");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("apellidos");
                               
                
                /*se agrega la fila a la tabla*/
                totalregistros++;
                modelo.addRow(registro);
            }

            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    public  String[] cargarDatosEspecificos(String codigo){  //fijate que al tipo de dato de retorno
       
        //el codigo que pide esta funcion es el codigo de un producto en especifico del cual la funcion
        //traera todos los datos relacionados para mostrarlos sobre una plantilla(cajas de texto en el formulario)
        //que podra ser modificada por el usuario
        //todos los datos encontrados se registran en un array de string el cual sera devuelto a la linea donde se invoco
        
        String [] datos = new String[12];
        sSQL="select per.codigoPersona, per.numeroDocumento, per.apellidos, per.nombre, per.telefono, per.tipoPersona, per.direccion,"
                + " per.correo, per.operatividad, emp.cargo, emp.login, emp.password FROM tblpersona per inner join tblempleado emp"
                + " on per.codigoPersona= emp.codigoPersona where per.codigoPersona=" +codigo+" and per.tipoPersona='E'";     
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                datos[0] = rs.getString("codigoPersona");
                datos[1] = rs.getString("numeroDocumento");
                datos[2] = rs.getString("apellidos");
                datos[3] = rs.getString("nombre");
                datos[4] = rs.getString("telefono");
                datos[5] = rs.getString("tipoPersona");
                datos[6] = rs.getString("direccion");
                datos[7] = rs.getString("correo");
                datos[8] = rs.getString("operatividad");               
                datos[9] = rs.getString("cargo");  
                datos[10] = rs.getString("login");
                datos[11] = rs.getString("password");
                
            }
            
            return datos;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
        
    }
    
    public boolean insertar(VEmpleado empleado) {
        sSQL = "insert into tblpersona(numeroDocumento,apellidos, nombre, telefono, tipopersona, direccion, correo, operatividad)"
                + " values (?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into tblempleado(codigoPersona, cargo, login, password)"
                + " values ((select codigoPersona from tblpersona order by codigoPersona desc limit 1),?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, empleado.getNumeroDocumento());
            pst.setString(2, empleado.getApellidos());
            pst.setString(3, empleado.getNombre());
            pst.setString(4, empleado.getTelefono());
            pst.setString(5, empleado.getTipoPersona());
            pst.setString(6, empleado.getDireccion());
            pst.setString(7, empleado.getCorreo());
            pst.setString(8, empleado.getOperatividad());

            
            pst2.setString(1, empleado.getCargo());
            pst2.setString(2, empleado.getLogin());
            pst2.setString(3,empleado.getPassword());
           
            
            

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {                    
                    return true;                        
                } else {                    
                    return false;
                }
            } else {                
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            
            return false;
        }
    }
    
    public boolean editar(VEmpleado datos) {
       sSQL = "update tblpersona set apellidos=?,nombre=?,telefono=?,tipoPersona=?,direccion=?,"
               + "correo=?, operatividad=? where codigoPersona=?";                
       sSQL2 = "update tblempleado set cargo=?, login=? , password=? where codigoPersona=?";
       try {
           PreparedStatement pst = cn.prepareStatement(sSQL);
           PreparedStatement pst2 = cn.prepareStatement(sSQL2);

           pst.setString(1, datos.getApellidos());
           pst.setString(2, datos.getNombre());
           pst.setString(3, datos.getTelefono());
           pst.setString(4, datos.getTipoPersona());
           pst.setString(5, datos.getDireccion());
           pst.setString(6, datos.getCorreo());
           pst.setString(7, datos.getOperatividad());
           pst.setInt(8, datos.getCodigoPersona());


           pst2.setString(1, datos.getCargo());
           pst2.setString(2,datos.getLogin());
           pst2.setString(3,datos.getPassword());
           pst2.setInt(4,datos.getCodigoPersona());

           int n = pst.executeUpdate();

           if (n != 0) {
               int n2 = pst2.executeUpdate();

               return n2 != 0;

           } else {
               return false;
           }

       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }

    public boolean eliminar(VEmpleado datos) {
              
        
         //Realmente no elimina ningun dato, solo se pone estado de no operativo
        sSQL = "update tblpersona set operatividad=? where codigoPersona=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,"N");
            pst.setInt(2,datos.getCodigoPersona()); //Se envia una N  a este campo pues se acordo que este es el 
                                  //simbolo para detonar una categoria No operativa           
                                    
            int n = pst.executeUpdate();
            
            if(n!=0){
                return true;                
            }
            else{
                return false;
            }
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
    public boolean ingresar(String login, String password){
        
//         sSQL="select emp.idEmpleado , emp.cargo, per.nombre from tblpersona per inner join tblempleado emp "
//                 + "on per.codigoPersona= emp.codigoPersona where emp.login ="+ login +" and emp.password=" + password;     
//       
         sSQL="select emp.idEmpleado , emp.cargo, per.nombre from tblpersona per inner join tblempleado emp on"
                 + " per.codigoPersona= emp.codigoPersona where emp.login='"+login+"' && emp.password='"+password+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                usuario[0] = rs.getString("idEmpleado");
                usuario[1] = rs.getString("cargo");
                usuario[2] = rs.getString("nombre");                
                
            }
            
            if (usuario[0]==null){
                return false;
            }else{
                return true;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    
}
