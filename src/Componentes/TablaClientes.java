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
    public TablaClientes(){
      this.Lista_TELL=new ArrayList<Integer>();
      this.Lista_TS=new ArrayList<Integer>();
    }
    
    public void generarTablaClientes(){
       for(int i=0;i<this.Lista_TELL.size();i++){
         this.clientes.add(new Clientes(i,this.Lista_TELL.get(i),this.Lista_TS.get(i)));
       }
    }
    
    
    public void setTELL(ArrayList<Integer> LTELL){
       this.Lista_TELL=LTELL;
    }
    public void setTS(ArrayList<Integer> LTS){
       this.Lista_TS=LTS;
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
    public int searchClientInServer(int indexS){
      for(int i=0;i<this.clientes.size();i++){
          if(indexS==this.clientes.get(i).getNroS())
             return i;  
      }
      return -1;
    }
    
    
    public ArrayList<Clientes> getTabla(){
      return this.clientes;
    }

    public Clientes getFromList(int index){
      System.out.println("index " + index);
      return this.clientes.get(index);
    }
    
}
