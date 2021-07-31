/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Contiene la lista de eventos del sistema
 * El tiempo de llegada actual y los tiempos de servicios de cada servidor 
 * @author Daniel Bermudez
 */
public class TablaEventos {
    private Evento evento;
    //Lleva un registro de los cambios de las variables de evento
    private ArrayList<Evento> registro_eventos; 
    
    public TablaEventos(){
      this.evento=new Evento();
      this.registro_eventos=new ArrayList<Evento>();
    }
    
     //Agrega el ultimo cambio hecho al registro
    public void updateRegistro(){
       this.registro_eventos.add(this.evento);
    }
    
    public Evento getEvento(){
       return this.evento;
    }
     
     public ArrayList<Evento> getRegistro(){
       return this.registro_eventos;
    } 
     
     
    
}
