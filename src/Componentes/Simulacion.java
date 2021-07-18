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
public class Simulacion {
    private int nro_Evento;
    private String tipo;
    private int nro_cliente;
    
    public Simulacion(int nroE,String tipo,int nroC){
      this.nro_Evento=nroE;
      this.tipo=tipo;
      this.nro_cliente=nroC;
    }
    
    public int getNro_Evento(){
      return this.nro_Evento;
    }
    
    public String getTipoEvento(){
      return this.tipo;
    }
    
    public int getNroCliente(){
      return this.nro_cliente;
    }
}
