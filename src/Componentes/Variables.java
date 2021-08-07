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
public class Variables {

  private ArrayList<Boolean> serverStatusList; //Lista de los Status de los servidores
  private int WL; //Cantidad de clientes en espera en el tiempo actual de simulacion
  private int numCliente; //Cantidad de clientes tanto espera como en servicio
  private int tiempoSalida; //Cantidad de clientes tanto espera como en servicio

  public Variables() {
    this.numCliente = 0;
    this.tiempoSalida = 999;
  }

  public void nextDT(int numCliente, int tiempoSalida) {
    this.numCliente = numCliente;
    this.tiempoSalida = tiempoSalida;
  }

  public int getNumCliente() {
    return numCliente;
  }

  public int getTiempoSalida() {
    return tiempoSalida;
  }
}
