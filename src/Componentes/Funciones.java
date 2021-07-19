/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;

/**
 * Clase que contiene todas las funciones para la obtencion de datos en la simulacion 
 * usando los parametros de entrada
 * @author Daniel Bermudez
 */
public class Funciones {
    //Nose si vamos a requerir esto pero ahi lo marco
    private double llegadasPromedio;
    private double tiempoEntreLLegadas;
    private double tiempoEntreLLegadasPromedio;
    private int cantidadLlegadas;
    
    private double salidasPromedio;
    private double tiempoDeServicio;
    private double tiempoDeServicioPromedio;
    private int cantidadServicio;
    //---------------------------------------------
    

    
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
 
    
     public Funciones(int numServers) {
        this.llegadasPromedio = 0;
        this.tiempoEntreLLegadas=0;
        this.tiempoEntreLLegadasPromedio=0;
        this.cantidadLlegadas=0;
        
        this.salidasPromedio=0;
        this.tiempoDeServicio=0;
        this.tiempoDeServicioPromedio=0;
        this.cantidadServicio=0;
        
       
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
       
    };
     
    public void CalcularPromedios(int timeModeling){
        //this.tiempoEntreLLegadasPromedio();
        //this.llegadasPromedio();
        //this.tiempoDeServicioPromedio();
        //this.salidasPromedio();
        //this.CalcularEstabilidad();
        //this.calcularProbabilidadEsperar();
        //this.calcularCantidadPromedioClientesEnCola(timeModeling);
        //this.calcularCantidadPromedioClientesEnSistema(timeModeling);
        //this.calcularTiempoPromedioClienteEnSistema();
        //this.calcularTiempoPromedioClienteEnCola();
        //this.calcularPorcentajeUtilizacion(timeModeling);
        //this.calcularPorcentajeUtilizacionGeneral();
    };
    
    public void setValoresEntrada(TablaDistribuciones tabla){
        this.cantidadLlegadas = tabla.getTabla().size();
        int salida,siguiente;
        for(int i=0,j=1;j<tabla.getTabla().size();i++,j++){
            salida = tabla.getTabla().get(i).gettiempo();
            siguiente = tabla.getTabla().get(j).gettiempo();
            this.tiempoEntreLLegadas = this.tiempoEntreLLegadas + siguiente - salida;
        }
            
    }
    
    public void tiempoEntreLlegadas(int tiempo){
        if(this.tiempoEntreLLegadas==0){
            this.tiempoEntreLLegadas=tiempo;
            this.cantidadLlegadas=this.cantidadLlegadas+1;
        }else{
            this.tiempoEntreLLegadas=this.tiempoEntreLLegadas+tiempo;
            this.cantidadLlegadas=this.cantidadLlegadas+1;
        }
    }
    
   /* 
    public void tiempoEntreLLegadasPromedio(){
        this.tiempoEntreLLegadasPromedio=this.tiempoEntreLLegadas/this.cantidadLlegadas;
    }
   */
    
   /* 
    private void llegadasPromedio(){
        this.llegadasPromedio=1/this.tiempoEntreLLegadasPromedio;
    }
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
    
    /*
    public void tiempoDeServicioPromedio(){
        this.tiempoDeServicioPromedio = this.tiempoDeServicio/this.cantidadServicio;
    }
    */
    
    /*
    private void salidasPromedio(){
        this.salidasPromedio=1/this.tiempoDeServicioPromedio;
    }
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
    
    public void actualizarTiempoClienteEnSistema(int entrada,int salida,int nroCliente){
        this.tiempoEnSistema = this.tiempoEnSistema+(salida-entrada);
    }
    
    public void calcularTiempoPromedioClienteEnSistema(){
        this.tiempoEnSistema = this.tiempoEnSistema/this.cantidadLlegadas;
    }
    
     public void actualizarTiempoClienteEnCola(int entrada,int salida,int servicio,int nroCliente){
        this.tiempoEnCola = this.tiempoEnCola+(salida-entrada-servicio);
    }
    public void calcularTiempoPromedioClienteEnCola(){
        this.tiempoEnCola = this.tiempoEnCola/this.cantidadLlegadas;
    }
    
    public void calcularTiempoAdicional(int timeModeling, int finishTime){
        this.tiempoAdicional = timeModeling-finishTime;
    }
    
     public void relacionOptima(int costoEsperaCliente, int costoServidor){
        this.relacionOptima = (this.clientesEnCola*costoEsperaCliente)/costoServidor;
    }
    
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
    public void actualizarPorcentajes(int prevTimeModeling, int timeModeling,ArrayList<Boolean> statusServer){
        for(int i=0;i<statusServer.size();i++){
            int uso;
            if(statusServer.get(i)!=false)
                uso=1;
            else
                uso=0;
            this.actualizarPorcentajeUtilizacion(prevTimeModeling,timeModeling,i,uso);
        }
    }
    
    public void calcularEstadisticasGenerales(Funciones e){
        this.clientesNoEsperan=this.clientesNoEsperan+e.clientesNoEsperan;
        this.clientesSeVan = this.clientesSeVan + e.clientesSeVan;
        this.cantidadLlegadas = this.cantidadLlegadas + e.cantidadLlegadas;
        this.calcularProbabilidadEsperar();
        this.clientesEnCola = this.clientesEnCola + e.clientesEnCola;
        this.clientesEnSistema = this.clientesEnSistema + e.clientesEnSistema;
        //this.porcentajeUtilizacionGeneral = ((this.porcentajeUtilizacionGeneral*(this.numEtapas-1))+e.porcentajeUtilizacionGeneral)/this.numEtapas;
        this.tiempoAdicional = e.tiempoAdicional;
        this.tiempoEnCola = this.tiempoEnCola + e.tiempoEnCola;
        this.tiempoEnSistema = this.tiempoEnSistema + e.tiempoEnSistema;
    }
    
    public void actualizarEstadisticasPromedio(Funciones e){
        this.llegadasPromedio = this.llegadasPromedio + e.llegadasPromedio;
        this.tiempoEntreLLegadas = this.tiempoEntreLLegadas + e.tiempoEntreLLegadas;
        this.tiempoEntreLLegadasPromedio= this.tiempoEntreLLegadasPromedio+ e.tiempoEntreLLegadasPromedio;
        this.cantidadLlegadas= this.cantidadLlegadas + e.cantidadLlegadas;
        
        this.salidasPromedio=this.salidasPromedio + e.salidasPromedio;
        this.tiempoDeServicio=this.tiempoDeServicio + e.salidasPromedio;
        this.tiempoDeServicioPromedio= this.tiempoDeServicioPromedio+ e.tiempoDeServicioPromedio;
        this.cantidadServicio=this.cantidadServicio+e.cantidadServicio;
        

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
}
