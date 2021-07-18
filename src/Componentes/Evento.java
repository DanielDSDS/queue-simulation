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
    private ArrayList<Integer> Lista_DT; //Lista de los tiempo de salida de cada servidor
    
    public Evento(){
      this.AT=0;
      this.Lista_DT=new ArrayList<Integer>();
    };
    
    public int getAT(){
      return this.AT;
    }
    
    public void setAT(int at){
      this.AT=at;
    }
    
    public ArrayList<Integer> getLista(){
      return this.Lista_DT;
    }
    
    public void setDT(int dt){
      this.Lista_DT.add(dt);
    }
    
    public int getDT(int i){
       return this.Lista_DT.get(i);
    }
    
    public void updateDT(int i,int dt){
      this.Lista_DT.set(i, dt);
    }
    
}