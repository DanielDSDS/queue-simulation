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
    
    public int getTiempo(){
      return this.tiempo;
    }

    public void setTiempo(int tiempo){
      this.tiempo = tiempo;
    }
    
    public double getProbabilidad(){
      return this.probabilidad;
    }
    
    public void setProbabilidad(double probabilidad){
      this.probabilidad=probabilidad;
    }
    
    public double getProbabilidadAcumulada(){
      return this.probalidadAcumulada;
    };
    
    public void setProbabilidadAcumulada(double probabilidad){
      this.probalidadAcumulada=probabilidad;
    };
    
    public double getValorMax(){
      return this.probalidadAcumulada - 1;
    };
    
    public double getValorMin(){
      return this.valorMin;
    };
    
    public void setValorMin(double valorMin){
      this.valorMin=valorMin;
    };
    
    public void setValorMax(double valorMax){
      this.valorMax = valorMax;
    };
    
    @Override
    public String toString() {
        return "Valor = " + this.tiempo + ", Porcentaje = " + this.probabilidad + ", Porcentaje acumulado = " + this.probalidadAcumulada + ", Intervalo = (" + this.valorMin + "," + this.getValorMax() + ")";
    }

}
