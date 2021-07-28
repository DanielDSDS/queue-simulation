package Componentes;

import Componentes.Aleatorio;
import Vistas.Salida;

public class Sistema {
    private int timeModeling;
    private int prevTimeModeling;
    private StatusServers statusServer;
    private TablaWaiting waitingLength;
    private TablaArrivals tablaArrival;
    private TablaArrivals tablaSistema;
    private int arrivalTime;
    private TablaDepartures departureTime;
    private TablaArrivals salidasSistema;
    private int finishTime;
    private int numEtapa;
    private int numEvent;
    private int numClientEntrada;
    private int numClientSalida;
    private int numClientSistem;
    private int numClientMax;
    private int costoEsperaClient;
    private int costoDeServidor;
    private String tipoEvent;
    private TablaDistribuciones tablaLLegadas;
    private TablaDistribuciones tablaServicio;
    private Estadisticas E;
    private Salida S;

    /**
     * @param tablaArrival Indica las entradas previstas para la etapa
     * @param numEtapa Indica a la etapa que representa en el sistema
     * @param numServers Indica el numero de servidores que tendra la etapa
     * @param finishTime Indica el Tiempo de Modelado maximo
     * @param numClientMax Indica el valor de los clientes maximos en el sistema
     * @param costoEsperaClient Indica el costo de espera de los clientes
     * @param costoServidor Indica el costo de un nuevo servidor
     * @param tablaLlegadas Representa los valores de tiempo entre llegadas y sus probabilidades
     * @param tablaServicio Representa los valores de tiempos de servicio y sus probabilidades
     * @param salida Representa la salida del sistemma
     */
      public Sistema(TablaArrivals tablaArrival, int numEtapa, int numServers, int finishTime, int numClientMax, int costoEsperaClient,int costoServidor,TablaDistribuciones tablaLlegadas,TablaDistribuciones tablaServicio,Salida salida ) {
        this.timeModeling = tablaArrival.nextArrival();           
        this.prevTimeModeling=0;
        this.statusServer = new StatusServers(numServers);
        this.waitingLength = new TablaWaiting(numServers,numClientMax);
        this.arrivalTime=0;
        this.departureTime = new TablaDepartures(numServers);
        this.salidasSistema = new TablaArrivals();
        this.tablaSistema = new TablaArrivals();
        this.tablaArrival = tablaArrival;
        this.finishTime = finishTime;
        this.numEtapa=numEtapa;
        this.numEvent=0;
        this.numClientEntrada=0;
        this.numClientSalida=0;
        this.numClientSistem=0;
        this.costoEsperaClient=costoEsperaClient;
        this.costoDeServidor=costoServidor;
        this.numClientMax= numClientMax;
        this.tipoEvent="Condiciones Iniciales";
        this.tablaLLegadas=tablaLlegadas;
        this.tablaServicio=tablaServicio;
        this.E= new Estadisticas(numServers);
        this.S = salida;
            
    }
    
    /**
     * Inicia la simulacion, con los parametros ya suministrados, entre ellos el tiempo de simulacion, 
     * tiempos entre llegadas, de servicio y a partir de ellos obtiene las estadisticas
     */
    public void iniciarSimulacion(){
        Aleatorio A = new Aleatorio();
        do{
            this.NextNumEvent();
            if((this.tablaArrival.nextArrival()<this.departureTime.nextDeparture() && 
                    this.timeModeling<this.finishTime && 
                    this.numEtapa==1) ||
                    (this.tablaArrival.nextArrival()<this.departureTime.nextDeparture() && 
                    !this.tablaArrival.isEmpty()&&
                    this.numEtapa!=1)){
                this.numClientEntrada=this.tablaArrival.nextClient();
                this.setTipoEvent("Llegada");
                this.prevTimeModeling=this.timeModeling;
                this.setTimeModeling(this.tablaArrival.nextArrival());
                this.E.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
                this.E.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.waitingLength.cantidadCola());
                this.E.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling, this.statusServer);
                if (this.statusServer.nextAvailableServer()==this.statusServer.getNumServers()){
                    int valor = this.waitingLength.addWaiting(this.numClientEntrada);
                    if(valor==0){
                        this.E.actualizarClientesSeVan();
                    }else{
                        this.addClientSistem();
                    }   
                }else{  
                    this.addClientSistem();
                    this.E.actualizarClientesNoEsperan();
                    this.statusServer.addClient(this.statusServer.nextAvailableServer(), this.numClientEntrada);
                    int aleatorio = A.generarNumero();
                    int tiempoServicio = this.tablaServicio.getTiempo(aleatorio);
                    this.E.tiempoDeServicio(tiempoServicio);
                    this.departureTime.addDeparture(this.timeModeling+tiempoServicio,tiempoServicio,this.numClientEntrada);
                    this.salidasSistema.addArrival(this.timeModeling+tiempoServicio,this.numClientEntrada);
                }
                this.tablaSistema.addArrival(this.tablaArrival.nextArrival(),this.tablaArrival.nextClient());
                this.tablaArrival.subArrival(this.numClientEntrada);
                if(this.numEtapa==1){
                    int aleatorio = A.generarNumero();
                    int tiempoLlegada = this.tablaLLegadas.getTiempo(aleatorio);
                    this.E.tiempoEntreLlegadas(tiempoLlegada);
                    this.tablaArrival.addArrival(this.timeModeling+tiempoLlegada,this.numClientEntrada+1);
                }           
                this.imprimir("Llegada");
            }else{  
                this.numClientSalida = this.departureTime.nextClient();
                this.setTipoEvent("Salida    ");
                this.prevTimeModeling=this.timeModeling;
                this.setTimeModeling(this.departureTime.nextDeparture());
                this.E.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
                this.E.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.waitingLength.cantidadCola());
                this.subClientSistem();
                this.E.actualizarTiempoClienteEnSistema(this.tablaSistema.valor(numClientSalida),this.departureTime.nextDeparture(), this.numClientSalida);
                this.E.actualizarTiempoClienteEnCola(this.tablaSistema.valor(numClientSalida),this.departureTime.nextDeparture(),this.departureTime.nextService(),this.numClientSalida);
                this.tablaSistema.subArrival(numClientSalida);
                this.E.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling, this.statusServer);
                if(this.waitingLength.isEmpty()){
                    this.statusServer.subClient(this.numClientSalida);
                    this.departureTime.subDeparture(this.numClientSalida);
                }else{
                    int servidor = this.statusServer.subClient(this.numClientSalida);
                    this.departureTime.subDeparture(this.numClientSalida);
                    int cliente = this.waitingLength.subWaiting();
                    this.statusServer.addClient(servidor,cliente);
                    int aleatorio = A.generarNumero();
                    int tiempoServicio = this.tablaServicio.getTiempo(aleatorio);
                    this.E.tiempoDeServicio(tiempoServicio);
                    this.departureTime.addDeparture(this.timeModeling+tiempoServicio,tiempoServicio,cliente);
                    this.salidasSistema.addArrival(this.timeModeling+tiempoServicio,cliente);
                }
                this.imprimir("Salida");
            }
        }while(this.timeModeling<this.finishTime || this.numClientSistem!=0 || (!this.tablaArrival.isEmpty() && this.numEtapa != 1) );
        this.E.CalcularPromedios(this.timeModeling);
        this.E.calcularTiempoAdicional(timeModeling, finishTime);
        this.E.relacionOptima(costoEsperaClient, costoDeServidor);
    }

    /**
     * Imprime las variables del sistema para observar la tabla de eventos cuando ocurre un nuevo evento
     * @param tipo Indica si se imprimira una salida o una llegada
     */
    public void imprimir(String tipo) {
        int cliente;
        if(tipo.equals("Llegada"))
            cliente=this.numClientEntrada;
        else
            cliente=this.numClientSalida;
    }
    /**
     * Imprime las estadisticas obtenidas de la simulacion 
     * @param unidad Unidad utilizada en la simulacion
     */
    public void imprimirEstadisticas(String unidad){
        S.addInfo(this.E.toString(this.numEtapa,unidad));
    }
    
    

    //----------------------------------------------------- Tipo Evento

    public TablaArrivals getSalidasSistema() {
        return salidasSistema;
    }

    /**
     * Establece el tipo de evento 
     * @param tipoEvent Indica si es una entrada, una salida o es la condicion inicial
     */
    public void setTipoEvent(String tipoEvent) {
        this.tipoEvent = tipoEvent;
    }
    //----------------------------------------------------- Numero de Clientes Maximo en el sistema

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
        this.numClientEntrada = this.numClientEntrada+1;
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
        this.numEvent = this.numEvent+1;
    }
    //------------------------------------------------------ Time Modeling

    /** 
     * Obtiene el valor del tiempo del Modelo
     * @return Tiempo de Modelo
     */
    public int getTimeModeling() {
        return timeModeling;
    }
    
    /**
     * Establece el valor del tiempo de Modelo
     * @param timeModeling Tiempo de Modelo 
     */
    public void setTimeModeling(int timeModeling) {
        this.timeModeling = timeModeling;
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
    public void addClientSistem() {
        this.numClientSistem = this.numClientSistem+1;
    }
    
    /**
     * Substrae un cliente del sistema
     */
    public void subClientSistem() {
        this.numClientSistem = this.numClientSistem-1;
    }

    public Estadisticas getEstadisticas() {
        return E;
    }
    
}