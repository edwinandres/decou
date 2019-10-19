/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author Edwin
 */
public class PValidacionNumerica {
    
    public boolean validarNumeros2(KeyEvent evt){
        char c=evt.getKeyChar();          
         
          if(Character.isLetter(c)
                  || Character.isWhitespace(evt.getKeyCode())) 
          { 
              getToolkit();               
              evt.consume(); 
              return true;
              //txtcedula.setToolTipText("Ingresa Solo Numeros");              
          }else{
              return false;
          }
        
    }
    
    public boolean validarNumeros(KeyEvent evt){
        char caracter = evt.getKeyChar();
        
        if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
            evt.consume();
            return true;
        }else{
            return false;
        }
    }

}
