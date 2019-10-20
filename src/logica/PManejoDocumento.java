/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Edwin
 */
public class PManejoDocumento {
    
    
    
    //crea un documento txt , para hacerlo pide los parametros(nombre, contenido)
    public void crearDocumentoTxt(String nombreArchivo,String contenido){
    
        File archivo;
        
        try{ 
            
            archivo = new File("C:\\archivos\\"+nombreArchivo+".txt");
            if (archivo.createNewFile()){//si el archivo se crea exitosamente
                JOptionPane.showMessageDialog(null, "El archivo se creo con exito");
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
            }else{
                //JOptionPane.showMessageDialog(null, "El archivo ya existe, trataremos de modificarlo");
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
            }


        }catch(Throwable e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public String leerDocumentoTxt(String nombreArchivo){
          
        String texto="";
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\archivos\\"+nombreArchivo+".txt"));
            String temp = "";
            String bfRead;
            
            //el ciclo se repite mientras bf tenga datos
            while((bfRead = bf.readLine()) != null){
                //se guarda el texto del archivo
                temp = temp + bfRead;
                JOptionPane.showMessageDialog(null, "El texto temporal es:\n "+temp);
            }
            
            texto = temp;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se encontro el documento");
        }
        return texto;
    }
    
}
