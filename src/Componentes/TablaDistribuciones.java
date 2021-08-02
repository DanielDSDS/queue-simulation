/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;
import Funciones.Alerta;

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
    private double proAcum=0;
    
   public TablaDistribuciones(ArrayList<Integer> t,ArrayList<Double> p){
      this.tiempos=t;
      this.probabilidades=p;
    };
   
   public TablaDistribuciones(){
       this.tiempos=new ArrayList<Integer>();
       this.probabilidades=new ArrayList<Double>();
   };
   
   public void addTiempo(int t,double p){
       System.out.println("probabilidad: "+p+" tiempo: "+t);
       System.out.println("acum "+proAcum);
     
       if(proAcum < 100){
        this.proAcum=proAcum+p;
        this.tiempos.add(t);
        this.probabilidades.add(p);
        Distribuciones dis=new Distribuciones(this.tiempos.get(this.tiempos.size()-1),this.probabilidades.get(this.probabilidades.size()-1));
        if(this.tabla.size()==0){
            dis.setProbabilidadAcumulada(this.probabilidades.get(this.probabilidades.size()-1));  
            dis.setValorMin(0);
            dis.getValorMax();
            tabla.add(dis);
          }
          else{
           dis.setProbabilidadAcumulada(this.tabla.get(this.tabla.size()-1).getProbabilidadAcumulada()+dis.getProbabilidad());
           
           dis.setValorMin(this.tabla.get(this.tabla.size()-1).getValorMax()+1);
           tabla.add(dis);     
          }
       }
       else{
         Alerta.mensajeError("La probabilidad acumulada ha llegado al 100% ");
       }
       System.out.println(this.toString());
   }
    
   public ArrayList<Distribuciones> generarTabla(){
        System.out.println("SIZE: "+tabla.size());
        int i=0;
        while(tabla.size()<tiempos.size()){
          System.out.println("SIZE Interator: "+tabla.size());
          Distribuciones dis=new Distribuciones(this.tiempos.get(i),this.probabilidades.get(i));
          if(i==0){
            dis.setProbabilidadAcumulada(this.probabilidades.get(i));  
            dis.setValorMin(0);
            dis.getValorMax();
            tabla.add(i,dis);
            i++;
          }
          else{
           dis.setProbabilidadAcumulada(this.tabla.get(i-1).getProbabilidadAcumulada()+dis.getProbabilidad());
           dis.setValorMin(this.tabla.get(i-1).getValorMax()+1);
           tabla.add(i,dis);   
           i++;   
          }
         
                  
        } 
        return this.getTabla();
    };
    
    public boolean completo(){
       System.out.println("completo? " + tabla.get(tabla.size()-1).getProbabilidadAcumulada());
        if(tabla.get(tabla.size()-1).getProbabilidadAcumulada()==100){
           return true;
        }
       return false;
    }
    public ArrayList<Distribuciones> getTabla(){
      return this.tabla;
    }
    
    public int getTiempo(int random){
        for(int i=0;i<this.tabla.size();i++){
            if(random>=this.tabla.get(i).getValorMin() && random<=this.tabla.get(i).getValorMax())
                return this.tabla.get(i).getTiempo();
        }
        return 0;
    }
    
    @Override
    public String toString(){
        String timeEntries = " Lista de tiempos: ";
        for (Distribuciones tiempo : this.tabla) {
            timeEntries = timeEntries+ "\n  " + tiempo.toString();
        }

        return timeEntries;
    }
    
}
