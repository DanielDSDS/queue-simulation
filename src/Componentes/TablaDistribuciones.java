/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Tabla de distribuciones 
 * 
 * @author Daniel Bermudez
 */
public class TablaDistribuciones {
    private ArrayList<Integer> tiempos;
    private ArrayList<Double> probabilidades;
    private ArrayList<Distribuciones> tabla=new ArrayList<Distribuciones>(); 
    
   public TablaDistribuciones(ArrayList<Integer> t,ArrayList<Double> p){
      this.tiempos=t;
      this.probabilidades=p;
    };
    
    public ArrayList<Distribuciones> generarTabla(){
        System.out.println("SIZE: "+tabla.size());
        int i=0;
        while(tabla.size()<tiempos.size()){
          Distribuciones dis=new Distribuciones(this.tiempos.get(i),this.probabilidades.get(i));
          if(i==0){
            dis.setprobablidadAcumulada(this.probabilidades.get(i));  
            dis.setValorMin(0);
            dis.getvalorMax();
            tabla.add(dis);
            i++;
          }
          else{
           dis.setprobablidadAcumulada(this.tabla.get(i-1).getprobabilidadAcumulada()+dis.getprobabilidad());
           dis.setValorMin(this.tabla.get(i-1).getvalorMax()+1);
           tabla.add(dis);   
           i++;   
          }
         
                  
        } 
        return this.getTabla();
    };
    
    
    public ArrayList<Distribuciones> getTabla(){
      return this.tabla;
    } 
    
}
