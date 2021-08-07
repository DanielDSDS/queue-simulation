package Componentes;

import Componentes.Aleatorio;
import Vistas.Salida;
import java.util.*;
import java.util.ArrayList;

public class Sistema {

  private int simulationTime;
  private int prevSimulationTime;
  private int tiempoSiguienteLlegada;
  private StatusServers statusServers;
  private ArrayList<Variables> variables;
  private ColaEspera waitingList;
  private Aleatorio numAleatorioA;
  private Aleatorio numAleatorioB;
  private int arrivalTime;
  private TablaClientes clientes;
  //private TablaEventos eventos;
  private Evento evento;
  private int finishTime;
  private int numEvent;
  private int numClientEntrada;
  private int numClientSalida;
  private int numClientSistem;
  private int numClientMax;
  private int costoEsperaClient;
  private int costoDeServidor;
  private int costoOperacion;
  private int costoOperacionExtra;
  private String tipoEvent;
  private TablaDistribuciones tablaLLegadas;
  private TablaDistribuciones tablaServicios;
  private Funciones Funciones;
  private TablaSimulacion simulacion;
  private Salida Salida;

  public Sistema(
    int numServers,
    int finishTime,
    int numClientMax,
    int costoOperacionExtra,
    int costoOperacion,
    int costoEsperaClient,
    int costoServidor,
    TablaDistribuciones tablaLlegadas,
    TablaDistribuciones tablaServicios,
    Salida salida
  ) {
    this.simulationTime = 0;
    this.prevSimulationTime = 0;
    this.variables = new ArrayList<>();
    this.evento = new Evento();
    this.evento.setAT(0);
    this.waitingList = new ColaEspera(numClientMax, numServers);
    this.arrivalTime = 0;
    this.clientes = new TablaClientes();
    this.finishTime = finishTime;
    this.numAleatorioA = new Aleatorio();
    this.numAleatorioB = new Aleatorio();
    this.numEvent = 0;
    this.numClientEntrada = 0;
    this.numClientSalida = 0;
    this.numClientSistem = 0;
    this.tiempoSiguienteLlegada = 0;
    this.costoEsperaClient = costoEsperaClient;
    this.costoDeServidor = costoServidor;
    this.costoOperacion = costoOperacion;
    this.costoOperacionExtra = costoOperacionExtra;
    this.statusServers = new StatusServers(numServers);
    this.numClientMax = numClientMax;
    this.tipoEvent = "Condiciones Iniciales";
    this.tablaLLegadas = tablaLlegadas;
    this.tablaServicios = tablaServicios;
    this.Funciones = new Funciones(numServers);
    this.Salida = salida;

    for (int i = 0; i < numServers; i++) {
      variables.add(i, new Variables());
    }
  }

  public void comenzarSimulacion(String unidad) {
    Variables nextSalida;
    int clientIndex = 0;
    int waitingListIndex = 0;
    int tiempoSiguienteLlegada = 0;
    int tiempoSiguienteSalida = 0;
    int cantidadClientes = 0;
    int nextAvailableServer;
    int departedClientId;
    int tiempoLlegada;
    int tiempoServicio;
    int serverIndex;
    Clientes currentClient, nextCliente;

    do {
      nextSalida = this.getNextSalida();
      tiempoSiguienteSalida = nextSalida.getTiempoSalida();

      if (tiempoSiguienteLlegada <= tiempoSiguienteSalida) {
        tipoEvent = "Llegada";

        //----------------- Llegada ------------------------
        prevSimulationTime = simulationTime;
        simulationTime = tiempoSiguienteLlegada;

        Aleatorio numAleatorioA = new Aleatorio();
        Aleatorio numAleatorioB = new Aleatorio();
        int randomLlegada = numAleatorioA.generarNumero();
        int randomServicio = numAleatorioB.generarNumero();
        tiempoLlegada = tablaLLegadas.getTiempo(randomLlegada);
        tiempoServicio = tablaServicios.getTiempo(randomServicio);

        Funciones.actualizarPorcentajes(
          prevSimulationTime,
          simulationTime,
          statusServers
        );
        Funciones.actualizarCantidadLlegadas();

        Salida.addInfo("\n///////Tipo de evento: Llegada////////");
        Salida.addInfo("\n-> TM: " + simulationTime);
        Salida.addInfo("\nLongitud de cola: " + waitingList.getLongitudCola());

        //Si serverIndex es -1 todos estan ocupados
        if (!statusServers.hayServidorLibre()) {
          //Todos los servidores estan ocupados - Se coloca en cola

          if (waitingList.addClient(clientIndex) == 0) {
            //Capacidad maxima alcanzada, cliente se va
            Funciones.actualizarClientesSeVan();
            Salida.addInfo("\nCliente se va sin ser atendido");
          } else {
            //El cliente entra a la cola y al sistema
            Funciones.actualizarCantidadClientesEnCola(simulationTime);
            clientes.addClient(clientIndex, simulationTime);
            waitingList.addClient(clientIndex);
            cantidadClientes++;

            Salida.addInfo(
              "\n-> " + clientes.getClient(clientIndex).toString()
            );
            Salida.addInfo("\nCliente Nro " + clientIndex + " Entro en espera");
          }
        } else {
          Funciones.actualizarClientesNoEsperan();
          nextAvailableServer = statusServers.nextServer();
          statusServers.asignarCliente(nextAvailableServer, clientIndex);

          clientes.addClient(clientIndex, simulationTime);
          clientes.updateTS(clientIndex, tiempoServicio);
          Funciones.actualizarTiemposDeServicio(tiempoServicio);

          Salida.addInfo(
            "\n-> TELL: " + clientes.getClient(clientIndex).getTELL()
          );
          Salida.addInfo("\n-> TS: " + clientes.getClient(clientIndex).getTS());

          variables
            .get(nextAvailableServer)
            .nextDT(clientIndex, simulationTime + tiempoServicio);

          cantidadClientes++;
        }

        //Se actualiza el tiempo de modelado con el tiempo de llegada
        tiempoSiguienteLlegada = simulationTime + tiempoLlegada;
        //evento.setAT(serverIndex, tiempoSiguienteLlegada);
        Salida.addInfo("\n-> TM actualizado: " + simulationTime);
        Salida.addInfo("\n-> TSLL : " + tiempoSiguienteLlegada);
        Salida.addInfo("\n///////Fin llegada///////");
        clientIndex++;
      } else {
        //----------------------------------- Salida ------------------------------------------------
        tipoEvent = "Salida";

        prevSimulationTime = simulationTime;
        simulationTime = tiempoSiguienteSalida;

        Funciones.actualizarPorcentajes(
          prevSimulationTime,
          simulationTime,
          statusServers
        );

        cantidadClientes--;

        //ID del servidor con el tiempo mas cercano
        departedClientId = nextSalida.getNumCliente();

        //PASA UN ERROR OUT OF BOUNDS AQUI
        currentClient = clientes.getClient(departedClientId);

        //Se "se despacha el cliente"
        //currentClient.setNumServidor(-2);

        nextAvailableServer = statusServers.sacarCliente(departedClientId);
        Aleatorio numAleatorioB = new Aleatorio();
        int randomServicio = numAleatorioB.generarNumero();
        tiempoServicio = tablaServicios.getTiempo(randomServicio);

        Funciones.actualizarTiempoClienteEnSistema(
          currentClient.getTELL(),
          simulationTime
        );

        //La cola de espera no esta vacia
        if (!waitingList.isColaVacia()) {
          int firstClient = waitingList.getFirstClient(); //Obtiene el primer cliente de la cola
          waitingList.popClient(); //Remueve el cliente de la cola

          statusServers.asignarCliente(nextAvailableServer, firstClient);

          clientes.getClient(firstClient).setNumServidor(nextAvailableServer);

          clientes.updateTS(
            clientes.getClient(firstClient).getClientIndex(),
            tiempoServicio
          );

          Funciones.actualizarTiempoClienteEnCola(
            clientes.getClient(firstClient).getTELL(),
            simulationTime,
            clientes.getClient(firstClient).getTS()
          );

          Funciones.actualizarTiemposDeServicio(tiempoServicio);
          nextSalida.nextDT(firstClient, simulationTime + tiempoServicio);
        } else {
          //Si la cola esta vacia se resetea el servidor
          nextSalida.nextDT(0, simulationTime + tiempoServicio);
        }

        //Se actualiza TM con el DT
        Salida.addInfo("\n//////// Tipo de evento: Salida ///////: ");
        Salida.addInfo("\n-> TM: " + simulationTime);
        Salida.addInfo(
          "\n-> Cliente: " + departedClientId + " " + currentClient
        );
        Salida.addInfo("\n-> TELL: " + currentClient.getTELL());
        Salida.addInfo("\n-> TS: " + currentClient.getTS());
        Salida.addInfo("\n->TM actualizado: " + evento.nextSalida());
        Salida.addInfo("\n///////Fin salida///////");
      }
    } while (simulationTime <= finishTime);
    Funciones.calcularPromedios(simulationTime);
    Funciones.calcularCostos(
      costoEsperaClient,
      costoDeServidor,
      costoOperacionExtra,
      costoOperacion,
      simulationTime
    );
    Salida.setLabelsText(Funciones, unidad);
    Salida.getArchivoSalida().escribirArchivo(Funciones.generarSalida(unidad));
  }

  public Variables getNextSalida() {
    return Collections.min(
      variables,
      Comparator.comparing(v -> v.getTiempoSalida())
    );
  }

  /**
   * Obtiene la cantidad de clientes maxima que pueden haber en el sistema
   * @return Numero de clientes maximos permitidos en el sistema
   */
  public int getNumClientMax() {
    return numClientMax;
  }

  //------------------------------------------------------ Costo de Espera del cliente

  /**
   * Obtiene el costo de espera del cliente
   * @return Costo de espera del cliente
   */
  public int getCostoEsperaClient() {
    return costoEsperaClient;
  }

  //----------------------------------------------------- Numero del cliente entrada

  /**
   * Establece el numero del siguiente cliente de entrada
   */
  public void NextClientEntrada() {
    this.numClientEntrada = this.numClientEntrada + 1;
  }

  //------------------------------------------------------- Finish Time

  /**
   * Obtiene el valor para finalizar la simulacion
   * @return Valor final de la simulacion
   */
  public int getFinishTime() {
    return finishTime;
  }

  //------------------------------------------------------- Numero de Evento

  /**
   * Incrementa el numero del evento
   */
  public void NextNumEvent() {
    this.numEvent = this.numEvent + 1;
  }

  //------------------------------------------------------ Time Modeling

  /**
   * Obtiene el valor del tiempo del Modelo
   * @return Tiempo de Modelo
   */
  public int getTimeModeling() {
    return simulationTime;
  }

  /**
   * Establece el valor del tiempo de Modelo
   * @param timeModeling Tiempo de Modelo
   */
  public void setTimeModeling(int timeModeling) {
    this.simulationTime = timeModeling;
  }

  //-----------------------------------------------------Arrival Time

  /**
   * Establece el valor de la siguiente llegada
   * @param arrivalTime Tiempo de llegada
   */
  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  /**
   * Obtiene el valor del tiempo de la siguiente llegada
   * @return Tiempo de la siguiente llegada
   */
  public int getArrivalTime() {
    return arrivalTime;
  }

  //-----------------------------------------------------Numero de Clientes en el Sistema

  /**
   * Añade un cliente al sistema
   */
  public void addClient() {
    this.numClientSistem = this.numClientSistem + 1;
  }

  /**
   * Substrae un cliente del sistema
   */
  public void subClientSistem() {
    this.numClientSistem = this.numClientSistem - 1;
  }
}
