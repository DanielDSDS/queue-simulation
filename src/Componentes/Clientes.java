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
    private int NroS=-1;//Servidor en el que esta el cliente(-1) representa que no esta asignado a ningun servidor
    
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
    public void setNroS(int NroS){
        this.NroS=NroS;
    }
    public int getNroS(){
       return this.NroS;
    }
    
    @Override
    public String toString(){
        return "Cliente Nro: "+this.Nro+" /TS:  "+this.ts+" /TLL:"+this.tell;
    }
    
}
