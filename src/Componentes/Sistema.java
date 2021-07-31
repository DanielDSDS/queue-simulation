package Componentes;

import Componentes.Aleatorio;
import Vistas.Salida;
import java.util.ArrayList;

public class Sistema {
    private int timeModeling;
    private int prevTimeModeling;
    
    private StatusServers statusServer;
    private TablaWaiting waitingLength;
    private TablaArrivals tablaArrival;
    private TablaArrivals tablaSistema;
  
    private TablaVariables variables;
    private ArrayList<Integer> Lista_espera;
    
    private int arrivalTime;
    
    private TablaClientes clientes;
    private TablaEventos eventos;

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
    
    private Funciones F;

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
     */
      
     public Sistema(TablaArrivals tablaArrival, int numServers, int finishTime, int numClientMax, int costoEsperaClient,int costoServidor,TablaDistribuciones tablaLlegadas,TablaDistribuciones tablaServicio,Salida salida ){
       this.timeModeling=0;
       this.prevTimeModeling=0;
       this.variables=new TablaVariables();
       this.eventos=new TablaEventos();
       this.eventos.getEvento().setAT(0);
       for(int i=0;i<=numServers-1;i++){
        this.variables.getVariables().addServidor(true);
        this.eventos.getEvento().setDT(999);
       }
       this.Lista_espera=new ArrayList<Integer>();
       this.arrivalTime=0;
       this.clientes=new TablaClientes();
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
       this.F = new Funciones(numServers);
       this.S = salida;
       this.simulacion=new TablaSimulacion();
     }
    
      /**
     * Inicia la simulacion, con los parametros ya suministrados, entre ellos el tiempo de simulacion, 
     * tiempos entre llegadas, de servicio y a partir de ellos obtiene las estadisticas
     */
    
    public void generarTablaCliente(){
       ArrayList<Integer> Lista_TELL=new ArrayList<Integer>();
       ArrayList<Integer> Lista_TS=new ArrayList<Integer>();
       for(int i=0;i<this.numClientMax;i++){
          Aleatorio A=new Aleatorio(); 
          int num=A.generarNumero();
          int tiempoLLegada=this.tablaLLegadas.getTiempo(num);
          Aleatorio B=new Aleatorio(); 
          int numB=B.generarNumero();
          int tiempoServicio=this.tablaServicio.getTiempo(numB);
          Lista_TELL.add(tiempoLLegada);
          Lista_TS.add(tiempoServicio);
       }
       this.clientes.setTS(Lista_TS);
       this.clientes.setTELL(Lista_TELL);
       this.clientes.generarTablaClientes();
       System.out.println( this.clientes.toString());
      
    };  
      
      
    public void comenzarSimulacion(String unidad){
      this.generarTablaCliente();
      int indexCliente=0;
      Clientes actualCliente,nextCliente;
      int indexCola=0;
      boolean despachadoTodos=false;
      //this.simulacion.Add(0,"Cond.Inicial",-1);
      do{
      
        this.numEvent=this.numEvent+1;
        System.out.println("Index Cliente: "+indexCliente);
        System.out.println("Cantidad de clientes " + this.variables.getVariables().getCantClientes());
        System.out.println("--allIngresados " + this.clientes.allIngresados());
        System.out.println("--AT " + this.eventos.getEvento().getAT());
        System.out.println("--DT " + this.eventos.getEvento().nextSalida());

        if((this.eventos.getEvento().getAT()<this.eventos.getEvento().nextSalida() 
           && indexCliente<this.clientes.getTabla().size()     
           && this.timeModeling<this.finishTime
           && this.variables.getVariables().getCantClientes()<this.numClientMax
           && this.clientes.allIngresados()==false
           )
           ||
           (this.eventos.getEvento().getAT()<this.eventos.getEvento().nextSalida() 
           && indexCliente<this.clientes.getTabla().size()
           && this.variables.getVariables().getCantClientes()<this.numClientMax     
           && this.clientes.allIngresados()==false     
           )){
          //----------------- Llegada ------------------------ 
          System.out.println("///////////////Llegada");
          System.out.println("///////////////TM: " + this.timeModeling);
          actualCliente = this.clientes.getFromList(indexCliente);
          System.out.println("///////////////Cliente: " + indexCliente + " " + actualCliente );
          this.simulacion.Add(this.numEvent,"LLegada",indexCliente);
          this.setTipoEvent("Llegada");
          this.prevTimeModeling=this.timeModeling;
          this.variables.getVariables().setTM(this.timeModeling);
          this.F.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
          this.F.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.Lista_espera.size());
          this.F.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling,this.variables.getVariables().getListaSS()); 
          int indexServer=this.variables.getVariables().getAvaibleServer();
          if(indexServer==-1){
            //----------------- Todos los servidores estan ocupados - Se coloca en cola ------------------------ 
            System.out.println("Cliente Nro "+actualCliente.getNro()+" Entro en espera");
            this.Lista_espera.add(actualCliente.getNro());
            this.addClient();
            this.variables.getVariables().upCantClientes();
            this.variables.getVariables().upWL();
            if(indexCliente<this.clientes.getTabla().size()-1){ 
              System.out.println("Se actualiza AT con la suma de TM("+this.timeModeling+") y TELL("+this.clientes.getTabla().get(indexCliente+1).getTELL()+")");  
              this.eventos.getEvento().setAT(this.clientes.getTabla().get(indexCliente).getTELL()+this.timeModeling);
             }
             else System.out.println("Ultimo cliente ingresado");
          } else {
            //----------------- Hay servidores disponibles - Se atiende el cliente ------------------------ 
            this.addClient();
            this.F.actualizarClientesNoEsperan();
            this.F.tiempoDeServicio(actualCliente.getTS());
            this.variables.getVariables().upCantClientes();
            this.variables.getVariables().setStatusServidor(indexServer,false);
            actualCliente.setNroS(indexServer);
            //Suma de TM con DT 
            System.out.println("Se actualiza DT con la suma de TM("+this.timeModeling+") y TS("+actualCliente.getTS()+")");  
            this.eventos.getEvento().updateDT(indexServer,actualCliente.getTS()+this.timeModeling);
            //Si es el primer evento AT es el TELL directamente
            if(this.numEvent==0){
              System.out.println("Se actualiza AT con TELL("+this.clientes.getTabla().get(indexCliente).getTELL());  
              this.eventos.getEvento().setAT(this.clientes.getTabla().get(indexCliente).getTELL());
            } else {
              //Si no es el primer evento se suma el TELL con TM  
              //Suma de TM con TELL 
             if(indexCliente<this.clientes.getTabla().size()-1){ 
              System.out.println("Se actualiza AT con la suma de TM("+this.timeModeling+") y TELL("+this.clientes.getTabla().get(indexCliente).getTELL()+")");  
              this.eventos.getEvento().setAT(this.clientes.getTabla().get(indexCliente).getTELL()+this.timeModeling);
             }
             else System.out.println("Ultimo cliente ingresado");
            }
          }
          this.F.tiempoEntreLlegadas(this.clientes.getTabla().get(indexCliente).getTELL());
          indexCliente=indexCliente+1;
          System.out.println("Se actualiza TM con la AT("+this.eventos.getEvento().getAT()+")");  
          this.setTimeModeling(this.eventos.getEvento().getAT());
          System.out.println("--TM actualizado: " + this.eventos.getEvento().getAT());
          System.out.println("////////////////Fin llegada");
        } else {
          //----------------------------------- Salida ------------------------------------------------ 
          int indexS=this.eventos.getEvento().nextExit();
          System.out.println("////////////////Salida por el server: " + indexS);
          System.out.println("///////////////TM: " + this.timeModeling);
          int indexClienteSalida = this.clientes.searchClientInServer(indexS);
          actualCliente=this.clientes.getFromList(indexClienteSalida);
          System.out.println("///////////////Cliente: " + indexClienteSalida + " " + actualCliente );
          actualCliente.setNroS(-2);
          this.simulacion.Add(this.numEvent,"Salida",indexClienteSalida);
          this.prevTimeModeling=this.timeModeling;
          System.out.println("Se actualiza TM con la DT("+this.eventos.getEvento().nextSalida()+")");  
          this.setTimeModeling(this.eventos.getEvento().nextSalida());
          System.out.println("--TM actualizado: " + this.eventos.getEvento().nextSalida());
          this.variables.getVariables().setTM(this.timeModeling);
          this.F.actualizarCantidadClientesEnSistema(this.prevTimeModeling,this.timeModeling,this.numClientSistem);
          this.F.actualizarCantidadClientesEnCola(this.prevTimeModeling, this.timeModeling, this.Lista_espera.size());
          this.F.actualizarClientesSeVan();
          this.subClientSistem();
          this.variables.getVariables().susCantClientes();
          this.F.actualizarTiempoClienteEnSistema(actualCliente.getTELL(),this.eventos.getEvento().nextSalida(),indexClienteSalida);
          this.F.actualizarTiempoClienteEnCola(actualCliente.getTELL(),this.eventos.getEvento().nextSalida(),actualCliente.getTS(),indexClienteSalida);
          this.F.actualizarPorcentajes(this.prevTimeModeling, this.timeModeling, this.variables.getVariables().getListaSS());
    
          //---------------------------- La cola de espera no esta vacia ------------------------------
          if(!this.Lista_espera.isEmpty()){
            int clienteCola=this.Lista_espera.get(0);//Obtiene el primer cliente de la cola
            this.Lista_espera.remove(0);//Remueve el cliente de la cola
            System.out.println("Sale de la cola el cliente nro: "+clienteCola);
            this.clientes.getTabla().get(clienteCola).setNroS(indexS);
            this.F.tiempoDeServicio(this.clientes.generarTabla().get(clienteCola).getTS());
            //Suma de TM con TS 
            System.out.println("Se actualiza DT con la suma de TM("+this.timeModeling+") y TS("+this.clientes.generarTabla().get(clienteCola).getTS()+")");  
            this.eventos.getEvento().updateDT(indexS,this.clientes.generarTabla().get(clienteCola).getTS()+this.timeModeling);
            indexCola=indexCola+1;
            
          } else {
            this.eventos.getEvento().updateDT(indexS,999);
            this.variables.getVariables().setStatusServidor(indexS,true);
          }
          System.out.println("////////////////Fin salida");
        }
        this.eventos.updateRegistro();
        this.variables.updateRegistro();
     } while ((this.timeModeling<this.finishTime || this.variables.getVariables().getCantClientes()!=0|| !this.Lista_espera.isEmpty()) && !this.clientes.allDespachados());
     this.F.CalcularPromedios(this.timeModeling);
     this.F.calcularTiempoAdicional(timeModeling, finishTime);
     this.F.relacionOptima(costoEsperaClient, costoDeServidor);

     S.setLabelsText(this.F, unidad);
     //S.addInfo(this.F.toString(unidad));
     S.getArchivoSalida().escribirArchivo(this.F.toString(unidad));
  }; 
  

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
    public void addClient() {
        this.numClientSistem = this.numClientSistem+1;
    }
    
    /**
     * Substrae un cliente del sistema
     */
    public void subClientSistem() {
        this.numClientSistem = this.numClientSistem-1;
    }

}