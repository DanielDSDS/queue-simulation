/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

/**
 *
 * @author Daniel Bermudez
 */
public class Clientes {
    private int Nro; //Nro del cliente
    private int tell; //Tiempo entre llegadas
    private int ts; //Tiempo de servicio
    
    Clientes(int nro,int tell,int ts){
      this.Nro=nro;
      this.tell=tell;
      this.ts=ts;
    }
    
    public int getNro(){
      return this.Nro;
    }
    
    public int getTELL(){
      return this.tell;
    }
    
    public int getTS(){
      return this.ts;
    }
    
    
}
