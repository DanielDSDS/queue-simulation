/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Contiene la lista entera de los clientes que van a ingresar al sistema
 * asi como tambien sus respectivos tiempos entre llegadas y tiempo de salidas
 * 
 * @author Daniel Bermudez
 */
public class TablaClientes {
    private ArrayList<Integer> Lista_TELL;
    private ArrayList<Integer> Lista_TS;
    private ArrayList<Clientes> clientes=new ArrayList<Clientes>();
    
    public TablaClientes(ArrayList<Integer> lTELL,ArrayList<Integer> lTS){
      this.Lista_TELL=lTELL;
      this.Lista_TS=lTS;
    }
    
    public ArrayList<Clientes> generarTabla(){
      int i=0;  
      while(clientes.size()<Lista_TELL.size()){
         Clientes c=new Clientes(i+1,Lista_TELL.get(i),Lista_TS.get(i));
         clientes.add(c);
         i++;
      }
      
      return this.clientes;
    }
    
    public ArrayList<Clientes> getTabla(){
      return this.clientes;
    }
    
}
