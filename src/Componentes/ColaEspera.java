package Componentes;

import java.util.*;

public class ColaEspera {

  private int capacidad;
  private ArrayList<Integer> colaEspera;

  public ColaEspera(int capacidadClientes, int cantServidores) {
    this.capacidad = capacidadClientes - cantServidores;
    this.colaEspera = new ArrayList<>();
  }

  public int addClient(int numCliente) {
    if (colaEspera.size() < capacidad) {
      colaEspera.add(numCliente);
      return 1;
    }
    return -1;
  }

  public int getFirstClient() {
    return colaEspera.get(0);
  }

  public int popClient() {
    if (colaEspera.size() > 0) {
      int cliente = colaEspera.get(0);
      colaEspera.remove(0);

      return cliente;
    }

    return -1;
  }

  public boolean isColaVacia() {
    return colaEspera.isEmpty();
  }

  public int getLongitudCola() {
    return colaEspera.size();
  }

  @Override
  public String toString() {
    String salida = "* Linea de espera: \n";

    for (int i = 0; i < colaEspera.size(); i++) {
      salida +=
        " -> Posicion: " +
        (i + 1) +
        " ocupada por el cliente Nº: " +
        colaEspera.get(i) +
        "\n";
    }

    return salida;
  }
}
