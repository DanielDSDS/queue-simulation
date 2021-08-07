/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 *Contiene la lista entera de los clientes que van a ingresar al sistema
 * asi como tambien sus respectivos tiempos entre llegadas y tiempo de salidas
 *
 * @author Daniel Bermudez
 */
public class TablaClientes {

  private ArrayList<Clientes> clientes;

  public TablaClientes() {
    this.clientes = new ArrayList<>();
  }

  //Añadir cliente a tabla de clientes
  public void updateTS(int numCliente, int tiempoServicio) {
    this.clientes.get(numCliente).setTS(tiempoServicio);
  }

  //Añadir cliente a tabla de clientes
  public void addClient(int numCliente, int tiempoLlegada) {
    clientes.add(new Clientes(numCliente, tiempoLlegada));
  }

  //Obetener cliente que esta siendo atendido por el servidor de id numServidor
  public int getClientFromServerId(int numServidor) {
    for (int i = 0; i < this.clientes.size(); i++) {
      if (numServidor == this.clientes.get(i).getNumServidor()) return i;
    }
    //Cliente no esta asignado a ningun servidor
    return -1;
  }

  public ArrayList<Clientes> getClients() {
    return this.clientes;
  }

  public Clientes getClient(int numCliente) {
    return this.clientes.get(numCliente);
  }

  //Retorna true si todos los clientes salieron del sistema
  //Retorna false si quedan clientes en el sistema
  //Numero de servidor -2 significa que salio del sistema
  public boolean allDespachados() {
    for (int i = 0; i < this.clientes.size(); i++) {
      if (this.clientes.get(i).getNumServidor() != -2) return false;
    }
    return true;
  }

  //Retorna true si todos los clientes fueron asignados a un servidor
  //Retorna false si quedan clientes en cola o por ser atendidos
  public boolean allProcesados() {
    for (int i = 0; i < this.clientes.size(); i++) {
      if (this.clientes.get(i).getNumServidor() == -1) return false;
    }
    return true;
  }

  //funcion para imprimir
  @Override
  public String toString() {
    String clienteEntries = " Lista de clientes: ";
    for (Clientes cliente : this.clientes) {
      clienteEntries = clienteEntries + "\n  " + cliente.toString();
    }

    return clienteEntries;
  }
}
