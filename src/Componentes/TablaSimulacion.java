/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Contiene el nro de evento en el tiempo de la simulacion
 * su tipo y que cliente esta involocruado en el
 * @author Daniel Bermudez
 */
public class TablaSimulacion {
    ArrayList<Simulacion> Lista_Simulacion = new ArrayList<Simulacion>();
    
    public void Add(int NroE,String Tipo,int NroC){
       Simulacion s=new Simulacion(NroE,Tipo,NroC);
       this.Lista_Simulacion.add(s);
    }
    
    public ArrayList<Simulacion> getTabla(){
      return this.Lista_Simulacion;
    }
    
    public int getNroEventoActual(){
        return this.Lista_Simulacion.get(this.Lista_Simulacion.size()).getNro_Evento();
    }
    
    
}
