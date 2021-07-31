/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *
 * @author Daniel Bermudez
 */
public class Variables {
    private int TM; //Tiempo de simulacion
    private ArrayList<Boolean> l_SS;//Lista de los Status de los servidores
    private int WL; //Cantidad de clientes en espera en el tiempo actual de simulacion
    private int cant_clientes; //Cantidad de clientes tanto espera como en servicio
    
    public Variables(){
       this.TM=0;
       this.l_SS=new ArrayList<Boolean>();
       this.WL=0;
       this.cant_clientes=0;
    }
    
    public int getTM(){
      return this.TM;
    }
    
    public void setTM(int tm){
      this.TM=tm;
    }
    
    public int getWL(){
       return this.WL;
    }
    
    public void upWL(){
      this.WL=this.WL+1; 
    }
    
    public void susWL(){
      this.WL=this.WL-1;
    }
    
    public int getCantClientes(){
       return this.cant_clientes;
    }
    
    public void upCantClientes(){
      this.cant_clientes=this.cant_clientes+1; 
    }
    
    public void susCantClientes(){
      this.cant_clientes=this.cant_clientes-1;
    }
    
    
    //Permite agregar un servidor nuevo a lista de estatus
    public void addServidor(Boolean ss){
      this.l_SS.add(ss);
    }
    
    public boolean getStatusServidor(int i){
      return this.l_SS.get(i);
    }
    
    public void setStatusServidor(int i,boolean status){
      this.l_SS.set(i, status);
    }
    
    public ArrayList<Boolean> getListaSS(){
       return this.l_SS;
    }
    
    public int getAvaibleServer(){
      for(int i=0;i<this.l_SS.size();i++){
        if(this.l_SS.get(i)){
            return i;
        }    
      };
      
      return -1;//Ningun servidor esta disponible
    }
   
    
}
