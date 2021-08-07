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

  private int indexCliente; //Nro del cliente
  private int TELL; //Tiempo entre llegadas
  private int TS; //Tiempo de servicio
  private int numServidor = -1; //Servidor en el que esta el cliente(-1) representa que no esta asignado a ningun servidor

  Clientes(int numCliente, int tell) {
    this.indexCliente = numCliente;
    this.TELL = tell;
  }

  public int getClientIndex() {
    return this.indexCliente;
  }

  public int getTELL() {
    return this.TELL;
  }

  public void setTELL(int tiempoLlegada) {
    this.TELL = tiempoLlegada;
  }

  public int getTS() {
    return this.TS;
  }

  public void setTS(int tiempoServicio) {
    this.TS = tiempoServicio;
  }

  public void setNumServidor(int numServidor) {
    this.numServidor = numServidor;
  }

  public int getNumServidor() {
    return this.numServidor;
  }

  @Override
  public String toString() {
    return (
      "Cliente Nro: " +
      this.indexCliente +
      " /TS:  " +
      this.TS +
      " /TLL:" +
      this.TELL
    );
  }
}
