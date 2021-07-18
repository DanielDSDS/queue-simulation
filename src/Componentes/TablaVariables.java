/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Contiene todas las variables pertinente del sistema durante la simulacion
 * Tiempo de la simulacion actual
 * Estatus de los servidores(o ya los servidores en si)
 * Cantidad de clientes en cola(o ya la cola en si)
 * Cantidad de clientes actual en el sistema(Tanto en servicio como en cola)
 * @author Daniel Bermudez
 */
public class TablaVariables {
    //Lista que lleva un registro secuencial de los cambios de las variables del sistema
    private ArrayList<Variables> registro_variables;
    private Variables variables; 
    
    public TablaVariables(){
       this.registro_variables=new ArrayList<Variables>();
       this.variables=new Variables();
    }
    
    //Agrega el ultimo cambio hecho al registro
    public void updateRegistro(){
       this.registro_variables.add(this.variables);
    }
    
    public Variables getVaribales(){
       return this.variables;
    }
    
    public ArrayList<Variables> getRegistro(){
       return this.registro_variables;
    }
    
}
