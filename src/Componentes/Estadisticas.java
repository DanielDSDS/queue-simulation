
package Componentes;

import java.util.ArrayList;

public class Estadisticas {
    private double llegadasPromedio;
    private double tiempoEntreLLegadas;
    private double tiempoEntreLLegadasPromedio;
    private int cantidadLlegadas;
    
    private double salidasPromedio;
    private double tiempoDeServicio;
    private double tiempoDeServicioPromedio;
    private int cantidadServicio;
   
    private double estabilidad;
    
    private double clientesNoEsperan;
   
    private double clientesSeVan;
    
    private double probabilidadDeEsperar;
    
    private double clientesEnCola;
    private double clientesEnSistema;
    private double tiempoEnCola;
    private double tiempoEnSistema;
    private double tiempoAdicional;
    private ArrayList<Double> porcentajeUtilizacion;
    private double porcentajeUtilizacionGeneral;
    private double relacionOptima;
    private int numEtapas;

    /**
     * @param numServers Indica el Numero de servidores que tendra la etapa
     */
    public Estadisticas(int numServers) {
        this.llegadasPromedio = 0;
        this.tiempoEntreLLegadas=0;
        this.tiempoEntreLLegadasPromedio=0;
        this.cantidadLlegadas=0;
        
        this.salidasPromedio=0;
        this.tiempoDeServicio=0;
        this.tiempoDeServicioPromedio=0;
        this.cantidadServicio=0;
        
        this.estabilidad = 0;
        this.clientesNoEsperan = 0;
        this.clientesSeVan = 0;
        
        this.probabilidadDeEsperar = 0;
        
        this.clientesEnCola = 0;
        this.clientesEnSistema = 0;
        
        this.tiempoEnCola = 0;
        this.tiempoEnSistema = 0;
        
        this.tiempoAdicional = 0;
        
        this.porcentajeUtilizacionGeneral = 0;
        this.relacionOptima = 0;
        this.porcentajeUtilizacion = new ArrayList<>();
        for(int i=0;i<numServers;i++)
            this.porcentajeUtilizacion.add(0.0); 
    }
    
    public void CalcularPromedios(int timeModeling){
        this.tiempoEntreLLegadasPromedio();
        this.llegadasPromedio();
        this.tiempoDeServicioPromedio();
        this.salidasPromedio();
        this.CalcularEstabilidad();
        this.calcularProbabilidadEsperar();
        this.calcularCantidadPromedioClientesEnCola(timeModeling);
        this.calcularCantidadPromedioClientesEnSistema(timeModeling);
        this.calcularTiempoPromedioClienteEnSistema();
        this.calcularTiempoPromedioClienteEnCola();
        this.calcularPorcentajeUtilizacion(timeModeling);
        this.calcularPorcentajeUtilizacionGeneral();
    }
    
    public void setValoresEntrada(TablaArrivals tabla){
        this.cantidadLlegadas = tabla.size();
        int salida,siguiente;
        for(int i=0,j=1;j<tabla.size();i++,j++){
            salida = tabla.getArrivalTimes().get(i).getArrivalTime();
            siguiente = tabla.getArrivalTimes().get(j).getArrivalTime();
            this.tiempoEntreLLegadas = this.tiempoEntreLLegadas + siguiente - salida;
        }
            
    }
    
    /**
     * Calcula el promedio de todos los tiempo entre llegadas de los clientes
     * @param tiempo Indica el tiempo entre llegada del cleinte
     */
    public void tiempoEntreLlegadas(int tiempo){
        if(this.tiempoEntreLLegadas==0){
            this.tiempoEntreLLegadas=tiempo;
            this.cantidadLlegadas=this.cantidadLlegadas+1;
        }else{
            this.tiempoEntreLLegadas=this.tiempoEntreLLegadas+tiempo;
            this.cantidadLlegadas=this.cantidadLlegadas+1;
        }
    }
    
    public void tiempoEntreLLegadasPromedio(){
        this.tiempoEntreLLegadasPromedio=this.tiempoEntreLLegadas/this.cantidadLlegadas;
    }
    
    /**
     * Calcula el valor de llegadas promedio al sistema
     */
    
    private void llegadasPromedio(){
        this.llegadasPromedio=1/this.tiempoEntreLLegadasPromedio;
    }

    /**
     * Calcula el promedio de todos los tiempo de servicio de los clientes
     * @param tiempo Indica el tiempo de servicio del cliente
     */
    public void tiempoDeServicio(int tiempo){
        if(this.tiempoDeServicio==0){
            this.tiempoDeServicio=tiempo;
            this.cantidadServicio=this.cantidadServicio+1;
        }else{
            this.tiempoDeServicio=this.tiempoDeServicio+tiempo;
            this.cantidadServicio=this.cantidadServicio+1;
        }
    }
    
    public void tiempoDeServicioPromedio(){
        this.tiempoDeServicioPromedio = this.tiempoDeServicio/this.cantidadServicio;
    }
    
    private void salidasPromedio(){
        this.salidasPromedio=1/this.tiempoDeServicioPromedio;
    }
    
    /**
     * Indica la cantidas de servidores que necesita la etapa para ser estable 
     * en caso de que no haya limite de clientes en el sistema
     */
    public void CalcularEstabilidad(){
        this.estabilidad=Math.ceil(this.llegadasPromedio/this.salidasPromedio);
    }

    /**
     * Cantidad de clientes que cuando llegan a la etapa hay algun 
     * servidor descocupado
     */
    public void actualizarClientesNoEsperan(){
        this.clientesNoEsperan=this.clientesNoEsperan+1;
    }
    
    /**
     * Cantidad de clientes que se van del sistema en caso de que este en su 
     * capacidad maxima a la hora de llegada
     */
    public void actualizarClientesSeVan(){
        this.clientesSeVan=this.clientesSeVan+1;
    }
    
    /**
     * Actualiza la probabilidad de esperar de los clientes cuando llegan 
     * a la etapa
     */
    public void calcularProbabilidadEsperar(){
        this.probabilidadDeEsperar=(this.cantidadLlegadas-this.clientesNoEsperan-this.clientesSeVan)/this.cantidadLlegadas;
    }
    
    /**
     * Actualiza la cantidad promedio de clientes en cola a lo largo de la 
     * simulacion
     * @param prev Indica el tiempo previo del Modelo
     * @param actual Indica el tiempo actual del Modelo
     * @param waitingLength Indica la longitud de la cola de la etapa
     */
    public void actualizarCantidadClientesEnCola(int prev, int actual,int waitingLength){
        if(actual==0)
            this.clientesEnCola=0;
        else{
            this.clientesEnCola=this.clientesEnCola+(actual-prev)*(waitingLength);
        }
    }
    
    public void calcularCantidadPromedioClientesEnCola(int tiempoFinal){
        this.clientesEnCola= this.clientesEnCola/tiempoFinal;
    }
    
    /**
     * Actualiza la cantidad promedio de clientes en el sistema a lo largo de
     * la simulacion 
     * @param prev Indica el tiempo previo del Modelo
     * @param actual Indica el tiempo actual del Modelo
     * @param numClientSistem Indica la cantidad de clientes en el sistema 
     */
    public void actualizarCantidadClientesEnSistema(int prev, int actual,int numClientSistem){
        if(actual==0)
            this.clientesEnSistema=0;
        else{
            this.clientesEnSistema= this.clientesEnSistema+(actual-prev)*(numClientSistem);
        }
    }
    
    public void calcularCantidadPromedioClientesEnSistema(int tiempoFinal){
        this.clientesEnSistema = this.clientesEnSistema/tiempoFinal;
    }
    
    /**
     * Actualiza el tiempo promedio que un cliente esta en el sistema
     * @param entrada Indica el tiempo de llegada del cliente
     * @param salida Indica el tiempo de salida del cliente
     * @param nroCliente Indica el valor numerico del cliente
     */
    public void actualizarTiempoClienteEnSistema(int entrada,int salida,int nroCliente){
        this.tiempoEnSistema = this.tiempoEnSistema+(salida-entrada);
    }
    
    public void calcularTiempoPromedioClienteEnSistema(){
        this.tiempoEnSistema = this.tiempoEnSistema/this.cantidadLlegadas;
    }

    /**
     * Actualiza el tiempo promedio que un cliente esta en cola 
     * @param entrada Indica el tiempo de llegada del cliente
     * @param salida Indica el tiempo de salida del cliente
     * @param servicio Indica el tiempo de servicio del cliente
     * @param nroCliente Indica el valor numerico del cliente
     */
    
    public void actualizarTiempoClienteEnCola(int entrada,int salida,int servicio,int nroCliente){
        this.tiempoEnCola = this.tiempoEnCola+(salida-entrada-servicio);
    }
    public void calcularTiempoPromedioClienteEnCola(){
        this.tiempoEnCola = this.tiempoEnCola/this.cantidadLlegadas;
    }
    
    
    /**
     * Calcula el tiempo adicional que estuvo activo el sistema comparado con 
     * el tiempo de simulacion previsto
     * @param timeModeling
     * @param finishTime
     */
    public void calcularTiempoAdicional(int timeModeling, int finishTime){
        this.tiempoAdicional = timeModeling-finishTime;
    }
    
    /**
     * Indica si la relacion de espera del cliente comparado con el costo de un 
     * nuevo servidor es la optima o no
     * @param costoEsperaCliente
     * @param costoServidor
     */
    public void relacionOptima(int costoEsperaCliente, int costoServidor){
        this.relacionOptima = (this.clientesEnCola*costoEsperaCliente)/costoServidor;
    }
    
    /**
     * Actualiza el porcentaje de utilizacion de uno de los servidores 
     * @param prev Indica el tiempo previo del modelo
     * @param tiempo Indica el tiempo actual del modelo
     * @param servidor Indica el numero del servidor a actualizar
     * @param uso Indica si durante el periodo estuvo en uso
     */
    public void actualizarPorcentajeUtilizacion(int prev,int tiempo,int servidor,int uso){
        if(prev==0 && tiempo==0)
            this.porcentajeUtilizacion.set(servidor,0.0);
        else
            this.porcentajeUtilizacion.set(servidor, (this.porcentajeUtilizacion.get(servidor)+(tiempo-prev)*uso));
    }
    
    public void calcularPorcentajeUtilizacion(int tiempoFinal){
        for(int i=0;i<this.porcentajeUtilizacion.size();i++){
            this.porcentajeUtilizacion.set(i, this.porcentajeUtilizacion.get(i)/tiempoFinal);
        }
    }
    
    
    /**
     * Actualiza el procentaje de utilizacion en general del sistema 
     */
    public void calcularPorcentajeUtilizacionGeneral(){
        double valorSumado=0;
        for(int i=0;i<this.porcentajeUtilizacion.size();i++)
            valorSumado = valorSumado + this.porcentajeUtilizacion.get(i);
        this.porcentajeUtilizacionGeneral = valorSumado/this.porcentajeUtilizacion.size();
    }
    
    /**
     * Actualiza los porcentajes de utilizacion de cada uno de los servidores
     * @param prevTimeModeling
     * @param timeModeling
     * @param statusServer
     */
    public void actualizarPorcentajes(int prevTimeModeling, int timeModeling,StatusServers statusServer){
        for(int i=0;i<statusServer.getNumServers();i++){
            int uso;
            if(statusServer.isOccupied(i)!=0)
                uso=1;
            else
                uso=0;
            this.actualizarPorcentajeUtilizacion(prevTimeModeling,timeModeling,i,uso);
        }
    }
    
    public void calcularEstadisticasGenerales(Estadisticas e){
        this.clientesNoEsperan=this.clientesNoEsperan+e.clientesNoEsperan;
        this.clientesSeVan = this.clientesSeVan + e.clientesSeVan;
        this.cantidadLlegadas = this.cantidadLlegadas + e.cantidadLlegadas;
        this.calcularProbabilidadEsperar();
        this.clientesEnCola = this.clientesEnCola + e.clientesEnCola;
        this.clientesEnSistema = this.clientesEnSistema + e.clientesEnSistema;
        this.porcentajeUtilizacionGeneral = ((this.porcentajeUtilizacionGeneral)+e.porcentajeUtilizacionGeneral)/this.numEtapas;
        this.tiempoAdicional = e.tiempoAdicional;
        this.tiempoEnCola = this.tiempoEnCola + e.tiempoEnCola;
        this.tiempoEnSistema = this.tiempoEnSistema + e.tiempoEnSistema;
    }
    
    public void actualizarEstadisticasPromedio(Estadisticas e){
        this.llegadasPromedio = this.llegadasPromedio + e.llegadasPromedio;
        this.tiempoEntreLLegadas = this.tiempoEntreLLegadas + e.tiempoEntreLLegadas;
        this.tiempoEntreLLegadasPromedio= this.tiempoEntreLLegadasPromedio+ e.tiempoEntreLLegadasPromedio;
        this.cantidadLlegadas= this.cantidadLlegadas + e.cantidadLlegadas;
        
        this.salidasPromedio=this.salidasPromedio + e.salidasPromedio;
        this.tiempoDeServicio=this.tiempoDeServicio + e.salidasPromedio;
        this.tiempoDeServicioPromedio= this.tiempoDeServicioPromedio+ e.tiempoDeServicioPromedio;
        this.cantidadServicio=this.cantidadServicio+e.cantidadServicio;
        
        this.estabilidad = this.estabilidad+e.estabilidad;
        this.clientesNoEsperan = this.clientesNoEsperan + e.clientesNoEsperan;
        this.clientesSeVan = this.clientesSeVan+e.clientesSeVan;
        
        this.probabilidadDeEsperar = this.probabilidadDeEsperar+e.probabilidadDeEsperar;
        
        this.clientesEnCola = this.clientesEnCola+e.clientesEnCola;
        this.clientesEnSistema = this.clientesEnSistema+e.clientesEnSistema;
        
        this.tiempoEnCola = this.tiempoEnCola+e.tiempoEnCola;
        this.tiempoEnSistema = this.tiempoEnSistema+e.tiempoEnSistema;
        
        this.tiempoAdicional = this.tiempoAdicional+e.tiempoAdicional;
        
        this.porcentajeUtilizacionGeneral = this.porcentajeUtilizacionGeneral+e.porcentajeUtilizacionGeneral;
        this.relacionOptima = this.relacionOptima+e.relacionOptima;
        for(int i=0;i<e.porcentajeUtilizacion.size();i++)
            this.porcentajeUtilizacion.set(i, this.porcentajeUtilizacion.get(i)+e.porcentajeUtilizacion.get(i)); 
    }
    
    public void calcularEstadisticasPromedio(int tiempo){
        this.llegadasPromedio = this.llegadasPromedio/tiempo;
        this.tiempoEntreLLegadas = this.tiempoEntreLLegadas/tiempo;
        this.tiempoEntreLLegadasPromedio= this.tiempoEntreLLegadasPromedio/tiempo;
        this.cantidadLlegadas= this.cantidadLlegadas/tiempo;
        
        this.salidasPromedio=this.salidasPromedio/tiempo;
        this.tiempoDeServicio=this.tiempoDeServicio/tiempo;
        this.tiempoDeServicioPromedio= this.tiempoDeServicioPromedio/tiempo;
        this.cantidadServicio=this.cantidadServicio/tiempo;
        
        this.estabilidad = this.estabilidad/tiempo;
        this.clientesNoEsperan = this.clientesNoEsperan/tiempo;
        this.clientesSeVan = this.clientesSeVan/tiempo;
        
        this.probabilidadDeEsperar = this.probabilidadDeEsperar/tiempo;
        
        this.clientesEnCola = this.clientesEnCola/tiempo;
        this.clientesEnSistema = this.clientesEnSistema/tiempo;
        
        this.tiempoEnCola = this.tiempoEnCola/tiempo;
        this.tiempoEnSistema = this.tiempoEnSistema/tiempo;
        
        this.tiempoAdicional = this.tiempoAdicional/tiempo;
        
        this.porcentajeUtilizacionGeneral = this.porcentajeUtilizacionGeneral/tiempo;
        this.relacionOptima = this.relacionOptima/tiempo;
        for(int i=0;i<this.porcentajeUtilizacion.size();i++)
            this.porcentajeUtilizacion.set(i, this.porcentajeUtilizacion.get(i)/tiempo); 
    }

    public String toString(int numEtapa,String unidad) {
        String porcentajeUtilizacionUnitario = "";
        for (int i=0;i<this.porcentajeUtilizacion.size();i++) {
            porcentajeUtilizacionUnitario = porcentajeUtilizacionUnitario +"  Servidor "+ (i+1) + ": " +
                    (this.porcentajeUtilizacion.get(i)*100)+" %,";
        }
        return  "\n   Estadisticas :" +
                "\n   Estabilidad = " + estabilidad + " servidores." +
                
                "\n   Cantidad de Llegadas = " + cantidadLlegadas + " llegadas." + 
                "\n   Llegadas Promedio = " + llegadasPromedio + " llegadas." +
                "\n   Tiempo entre LLegadas Promedio = " + tiempoEntreLLegadasPromedio + " " + unidad + "." +
                
                "\n   Cantidad de Salidas = " + cantidadServicio + " salidas." +
                "\n   Salidas Promedio = " + salidasPromedio +  " salidas." +
                "\n   Tiempo de Servicio Promedio = " + tiempoDeServicioPromedio +  " " + unidad + "." +
                
                "\n\n   Cantidad de veces que un clientes no espero = " + clientesNoEsperan + " veces."+
                "\n   Cantidad de veces que un cliente se fue = " + clientesSeVan + " veces."+
                
                "\n   Probabilidad de un cliente de esperar = " + probabilidadDeEsperar*100 + "%." +
                "\n   Cantidad de clientes promedio en cola = " + clientesEnCola + " clientes."+ 
                "\n   Cantidad de clientes promedio en el sistema = " + clientesEnSistema + " clientes."+
                
                "\n   Tiempo promedio de un cliente en cola = " + tiempoEnCola + " " + unidad + "." +
                "\n   Tiempo promedio de un cliente en sistema = " + tiempoEnSistema + " " + unidad + "." +
                "\n   Porcentaje de utilizacion de los servidores = " + porcentajeUtilizacionUnitario + 
                "\n   Porcentaje de utilizacion del sistema = " + porcentajeUtilizacionGeneral*100 + "%." +
                "\n   Relacion optima entre Costo de Espera del cliente y Costo de nuevo servidor:" +
                "\n         Recomendable agregar " + relacionOptima+" servidores.\n\n";
    }
    
    public String toStringGeneral(String unidad){
        return  " ESTADISTICAS GENERALES:" +                
                "\n   Cantidad de veces que un clientes no espero = " + clientesNoEsperan + " veces." +
                "\n   Cantidad de veces que un cliente se fue = " + clientesSeVan + " veces." +
                "\n   Probabilidad de un cliente de esperar = " + probabilidadDeEsperar*100 + "%."+
                "\n   Cantidad de clientes promedio en cola = " + clientesEnCola + " clientes."+
                "\n   Cantidad de clientes promedio en el sistema = " + clientesEnSistema + " clientes." +
                "\n   Tiempo promedio de un cliente en cola = " + tiempoEnCola + " "+ unidad + "." +
                "\n   Tiempo promedio de un cliente en sistema = " + tiempoEnSistema + " " + unidad + "." + 
                "\n   Tiempo adicional que el sistema estuvo abierto = " + tiempoAdicional + " " + unidad + "." +
                "\n   Porcentaje de utilizacion del sistema = " + porcentajeUtilizacionGeneral*100 + "%.\n"; 
    }

    
    
}
