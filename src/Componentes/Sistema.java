package Componentes;

import Componentes.Aleatorio;
import Vistas.Salida;
import java.util.ArrayList;

public class Sistema {
    private int timeModeling;
    private int prevTimeModeling;
    
   //----------Actual------------------ 
    private StatusServers statusServer;
    private TablaWaiting waitingLength;
    private TablaArrivals tablaArrival;
    private TablaArrivals tablaSistema;
  
  //----------Equivalente---------------
    private TablaVariables variables;
    private ArrayList<Integer> Lista_espera;
    
  //------------------------------------  
    
    private int arrivalTime;
    
  //------------ACTUAL---------------------.  
    private TablaDepartures departureTime;
    private TablaArrivals salidasSistema;
    
  //-----------EQUIVALENTE------------------
    private TablaClientes clientes;
    private TablaEventos eventos;
  //---------------------------------------  
    private int finishTime;
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
    
    //----------Actual------------
     private Estadisticas E;
    //----------Equivalente--------
     private Funciones F;
    //-----------------------------
    private TablaSimulacion simulacion; 
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
      
     public Sistema(TablaArrivals tablaArrival, int numServers, int finishTime, int numClientMax, int costoEsperaClient,int costoServidor,TablaDistribuciones tablaLlegadas,TablaDistribuciones tablaServicio,Salida salida ){
       this.timeModeling=tablaArrival.nextArrival();
       this.prevTimeModeling=0;
       this.variables=new TablaVariables();
       this.eventos=new TablaEventos();
       for(int i=0;i<=numServers;i++){
        this.variables.getVariables().addServidor(true);
        this.eventos.getEvento().setAT(0);
        this.eventos.getEvento().setDT(999);
       }
       this.Lista_espera=new ArrayList<Integer>();
       this.arrivalTime=0;
       this.clientes=new TablaClientes();

       
       this.salidasSistema = new TablaArrivals();
       this.tablaSistema = new TablaArrivals();
       this.tablaArrival = tablaArrival;
       this.finishTime = finishTime;
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
       this.F=new Funciones(numServers);
       this.S = salida;
       this.simulacion=new TablaSimulacion();
     }
    
    /*
      public Sistema(TablaArrivals tablaArrival, int numEtapa, int numServers, int finishTime, int numClientMax, int costoEsperaClient,int costoServidor,TablaDistribuciones tablaLlegadas,TablaDistribuciones tablaServicio,Salida salida ) {
        this.timeModeling = tablaArrival.nextArrival();           
        this.prevTimeModeling=0;
        //---------------------ACTUAL-----------------------------------
        this.statusServer = new StatusServers(numServers);
        this.waitingLength = new TablaWaiting(numServers,numClientMax);
        //--------------------EQUIVALENTE--------------------------------
        this.variables=new TablaVariables();
        this.Lista_espera=new ArrayList<Integer>();
        //---------------------------------------------------------------
        this.arrivalTime=0;
        //--------------------ACTUAL---------------------------------
        this.departureTime = new TablaDepartures(numServers);
        //-------------------EQUIVALENTE-----------------------------
        this.clientes=new TablaClientes();
        //-----------------------------------------------------------
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
        //------------Actual-----------------------
        this.E= new Estadisticas(numServers);
        //-----------Equivalente-------------------
        this.F=new Funciones(numServers);
        //-----------------------------------------
        this.S = salida;
            
    }
     */
    
    /**
     * Inicia la simulacion, con los parametros ya suministrados, entre ellos el tiempo de simulacion, 
     * tiempos entre llegadas, de servicio y a partir de ellos obtiene las estadisticas
     */
    
    public void generarTablaCliente(){
       Aleatorio A=new Aleatorio();
       ArrayList<Integer> Lista_TELL=new ArrayList<Integer>();
       ArrayList<Integer> Lista_TS=new ArrayList<Integer>();
       for(int i=0;i<=this.numClientMax;i++){
          int num=A.generarNumero();
          int tiempoLLegada=this.tablaLLegadas.getTiempo(num);
          int tiempoServicio=this.tablaServicio.getTiempo(num);
          Lista_TELL.add(tiempoLLegada);
          Lista_TS.add(tiempoServicio);
       }
       this.clientes.setTS(Lista_TS);
       this.clientes.setTELL(Lista_TELL);
       this.clientes.generarTablaClientes();
    };  
      
      
    public void comenzarSimulacion(){
      this.generarTablaCliente();
      int indexCliente=0;
      Clientes actualCliente,nextCliente;
      int indexCola=0;
      this.simulacion.Add(0,"Cond.Inicial",-1);
      do{
        this.numEvent=this.numEvent+1;
        if((this.eventos.getEvento().getAT()<this.eventos.getEvento().nextDeparture() 
           && this.timeModeling<this.finishTime
           )
           ||
           (this.eventos.getEvento().getAT()<this.eventos.getEvento().nextDeparture() 
           && indexCliente<this.clientes.getTabla().size()
           )){
          System.out.println("condicion 1");
          //equivalente a numClienteEntrada?
          int nextArrival = this.eventos.getEvento().getAT();
          System.out.println("indice llegada " + nextArrival);
          System.out.println("indice cliente " + indexCliente);
          indexCliente = this.clientes.searchClientInServer(nextArrival);
          actualCliente = this.clientes.getFromList(indexCliente);
          this.simulacion.Add(this.numEvent,"LLegada",indexCliente);
          this.setTipoEvent("Llegada");
          this.prevTimeModeling=this.timeModeling;
          this.setTimeModeling(actualCliente.getTELL());
          this.variables.getVariables().setTM(this.timeModeling);
          this.F.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
          this.F.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.Lista_espera.size());
          this.F.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling,this.variables.getVariables().getListaSS()); 
          int indexServer=this.variables.getVariables().getAvaibleServer();
          if(indexServer==-1){
            this.Lista_espera.add(actualCliente.getNro());
            this.addClientSistem();
            this.variables.getVariables().upCantClientes();
            this.variables.getVariables().upWL();
          }
          else{
            this.addClientSistem();
            this.variables.getVariables().upCantClientes();
            this.variables.getVariables().setStatusServidor(indexServer,false);
            this.eventos.getEvento().updateDT(indexServer,actualCliente.getTS());
            this.eventos.getEvento().setAT(this.clientes.getTabla().get(indexCliente+1).getTELL());
            actualCliente.setNroS(indexServer);
          }
          
        }
        else{
          System.out.println("condicion 2 " );
          int indexS=this.eventos.getEvento().nextExit();
          indexCliente=this.clientes.searchClientInServer(indexS);
          actualCliente=this.clientes.getFromList(indexCliente);
          this.simulacion.Add(this.numEvent,"Salida",indexCliente);
          this.prevTimeModeling=this.timeModeling;
          this.setTimeModeling(this.eventos.getEvento().nextDeparture());
          this.variables.getVariables().setTM(this.timeModeling);
          this.F.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
          this.F.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.Lista_espera.size());
          this.subClientSistem();
          this.variables.getVariables().susCantClientes();
          this.F.actualizarTiempoClienteEnSistema(actualCliente.getTELL(),this.eventos.getEvento().nextDeparture(),indexCliente);
          this.F.actualizarTiempoClienteEnCola(actualCliente.getTELL(),this.eventos.getEvento().nextDeparture(),actualCliente.getTS(),indexCliente);
          this.F.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling, this.variables.getVariables().getListaSS());
          int clienteCola=this.Lista_espera.get(indexCola);
          this.clientes.getTabla().get(clienteCola).setNroS(indexS);
          this.F.tiempoDeServicio(this.clientes.generarTabla().get(clienteCola).getTS());
          this.eventos.getEvento().updateDT(indexS,this.clientes.generarTabla().get(clienteCola).getTS()+this.timeModeling);
          this.eventos.getEvento().setAT(this.clientes.getTabla().get(indexCliente+1).getTELL());
          indexCola=indexCola+1;
        }
        this.eventos.updateRegistro();
        this.variables.updateRegistro();
     }while(this.timeModeling<this.finishTime || this.numClientEntrada!=0 || !this.tablaArrival.isEmpty());
     this.F.CalcularPromedios(this.timeModeling);
     this.F.calcularTiempoAdicional(timeModeling, finishTime);
     this.F.relacionOptima(costoEsperaClient, costoDeServidor);
  }; 
  
  /*    
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
                    int tiempoServicio = this.tablaServicio.getValue(aleatorio);
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
    */  

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
        S.addInfo(this.E.toString(1,unidad));
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