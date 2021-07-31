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
public class Evento {
    private int AT; //Tiempo de llegada del cliente al sistema
    private ArrayList<Integer> lista_DT; //Lista de los tiempo de salida de cada servidor 
    private ArrayList<Integer> lista_AT; //Lista de los tiempo de entrada de cada cliente 
    
    public Evento(){
      this.AT=0;
      this.lista_DT=new ArrayList<Integer>();
      this.lista_AT=new ArrayList<Integer>();
    };
    
    public int getAT(){
      return this.AT;
    }
    
    public void setAT(int at){
      System.out.println("--AT actualizado: "+at);
      this.AT=at;
    }
    
    public ArrayList<Integer> getLista(){
      return this.lista_DT;
    }
    
    public void setDT(int dt){
      this.lista_DT.add(dt);
    }
    
    public int nextSalida(){
       int nextD = 999;
       for(int i=0;i<this.lista_DT.size();i++){
           if(this.lista_DT.get(i)<nextD)
              nextD=this.lista_DT.get(i);
       }
       return nextD;
    }

    public int nextLlegada(){
       int nextA = 999;
       for(int i=0;i<this.lista_AT.size();i++){
           if(this.lista_AT.get(i) < nextA)
              nextA = this.lista_AT.get(i);
       }
       return nextA;
    }
    
    public int nextExit(){
       int nextD = 999;
       int indexS = 0;
       for(int i=0;i<this.lista_DT.size();i++){
           if(this.lista_DT.get(i)<nextD){
              nextD=this.lista_DT.get(i);
              indexS=i;
           }   
       }
       return indexS;
    }
    
    public int getDT(int i){
       return this.lista_DT.get(i);
    }
    public void updateDT(int i,int dt){
      this.lista_DT.set(i, dt);
      System.out.println("--DT actualizado del servidor"+i+": " + this.lista_DT.get(i));  
    }
    
}
