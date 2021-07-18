/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import javax.swing.JOptionPane;

public class Alerta {
    
    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"ERROR",JOptionPane.WARNING_MESSAGE);
    }
    
    public static void mensajeNotificacion(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"INFORMACION",JOptionPane.INFORMATION_MESSAGE);
    }
}
