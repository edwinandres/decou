/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.mysql.jdbc.Connection;
//import org.gjt.mm.mysql.Driver.class;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin
 */
public class Conexion {
    public String db="basedecou";
    public String url="jdbc:mysql://127.0.0.1/" + db;
    //private static final String URL = "jdbc:mysql://localhost:3306/bd_ejemplo";
    
    public String user="root";
    public String pass="";   
    
    /*
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bd_ejemplo";
    private static final String USUARIO = "root";
    private static final String CLAVE = "mysql";
    */

    public Conexion() {
    }
    
    public Connection conectar(){
        Connection link = null;
        
        try {
           Class.forName("org.gjt.mm.mysql.Driver");
           link =  (Connection) DriverManager.getConnection(this.url,this.user,this.pass);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return link;
    }
}
