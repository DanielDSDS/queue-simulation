package Componentes;

import java.util.ArrayList;

public class StatusServers {

  private ArrayList<Integer> servers;
  private final int numServers;

  public StatusServers(int numServers) {
    this.numServers = numServers;
    this.servers = new ArrayList<>();
    for (int i = 0; i < numServers; i++) this.servers.add(i, 0);
  }

  public ArrayList<Integer> getServidores() {
    return servers;
  }

  public int getCantServidores() {
    return numServers;
  }

  public void asignarCliente(int numServidor, int numCliente) {
    servers.set(numServidor, numCliente);
  }

  public int sacarCliente(int numCliente) {
    for (int i = 0; i < servers.size(); i++) {
      if (servers.get(i) == numCliente) {
        servers.set(i, 0);

        return i;
      }
    }

    return -1;
  }

  public int isOccupied(int index) {
    return servers.get(index);
  }

  public boolean hayServidorLibre() {
    for (int i = 0; i < servers.size(); i++) {
      if (isOccupied(i) == 0) {
        return true;
      }
    }

    return false;
  }

  public int nextServer() {
    for (int i = 0; i < servers.size(); i++) {
      if (isOccupied(i) == 0) {
        return i;
      }
    }
    return -1;
  }
}
