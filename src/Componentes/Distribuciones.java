/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

/**
 *Clase con la cual se obtiene las filas de la tabla de distrubiciones de probabilidad que se vayan a necesitar
 * para la simulacion
 * @author Brady
 */
public class Distribuciones {
     private int tiempo;
     private double probabilidad;
     private double probalidadAcumulada;
     /*Extremos del ranngo determinado*/
     private double valorMin;
     private double valorMax;
     
    public Distribuciones(int tiempo,double probabilidad ){
      this.tiempo=tiempo;
      this.probabilidad=probabilidad;
    }
    
    public int gettiempo(){
      return this.tiempo;
    }
    
    public double getprobabilidad(){
      return this.probabilidad;
    }
    
    public void setprobabilidad(double probabilidad){
      this.probabilidad=probabilidad;
    }
    
    public double getprobabilidadAcumulada(){
      return this.probalidadAcumulada;
    };
    
    public void setprobablidadAcumulada(double probabilidad){
      this.probalidadAcumulada=probabilidad;
    };
    
    public double getvalorMax(){
      this.valorMax=(this.probalidadAcumulada*100)-1;  
      return this.valorMax;
    };
    
    public double getValorMin(){
      return this.valorMin;
    };
    
    public void setValorMin(double valorMin){
      this.valorMin=valorMin;
    };
}