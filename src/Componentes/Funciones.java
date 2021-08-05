/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Funciones {
    public double llegadasPromedio;
    public double tiempoEntreLLegadas;
    public double tiempoEntreLLegadasPromedio;
    public int cantidadLlegadas;
    public double salidasPromedio;
    public double tiempoDeServicio;
    public double tiempoDeServicioPromedio;
    public int cantidadServicio;
    public double clientesNoEsperan;
    public double clientesSeVan;
    public double probabilidadDeEsperar;
    public double clientesEnCola;
    public double clientesEnSistema;
    public double tiempoEnCola;
    public double tiempoEnSistema;
    public double tiempoAdicional;
    public ArrayList<Double> porcentajeUtilizacion;
    public double porcentajeUtilizacionGeneral;
    public double costoDeServidor;
    public double costoOperacionExtra;
    public double costoOperacion;
    public double costoEspera;
 
    
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

        this.costoDeServidor = 0;
        this.costoOperacion = 0;
        this.costoOperacionExtra = 0;
        this.costoEspera = 0;
        
        this.porcentajeUtilizacionGeneral = 0;
        this.porcentajeUtilizacion = new ArrayList<>();
        for(int i=0;i<=numServers;i++)
            this.porcentajeUtilizacion.add(0.0);
       
    };
     
    public void calcularPromedios(int timeModeling){
        this.tiempoEntreLLegadasPromedio();
        this.llegadasPromedio();
        this.tiempoDeServicioPromedio();
        this.salidasPromedio();
        //this.CalcularEstabilidad();
        this.calcularProbabilidadEsperar();
        this.calcularCantidadPromedioClientesEnCola(timeModeling);
        this.calcularCantidadPromedioClientesEnSistema(timeModeling);
        this.calcularTiempoPromedioClienteEnSistema();
        this.calcularTiempoPromedioClienteEnCola();
        this.calcularPorcentajeUtilizacion(timeModeling);
        this.calcularPorcentajeUtilizacionGeneral();
    };
    
    public void setValoresEntrada(TablaDistribuciones tabla){
        this.cantidadLlegadas = tabla.getTabla().size();
        int salida,siguiente;
        for(int i=0,j=1;j<tabla.getTabla().size();i++,j++){
            salida = tabla.getTabla().get(i).getTiempo();
            siguiente = tabla.getTabla().get(j).getTiempo();
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
    
    public void tiempoEntreLLegadasPromedio(){
        this.tiempoEntreLLegadasPromedio=this.tiempoEntreLLegadas/this.cantidadLlegadas;
    }
    
    private void llegadasPromedio(){
        this.llegadasPromedio=1/this.tiempoEntreLLegadasPromedio;
    }

    public void tiempoDeServicio(int tiempo){
        if(this.tiempoDeServicio==0){
            this.tiempoDeServicio=tiempo;
            this.cantidadServicio=this.cantidadServicio+1;
        }else{
            this.tiempoDeServicio=this.tiempoDeServicio+tiempo;
            this.cantidadServicio=this.cantidadServicio+1;
        }
    }

    public void calcularCostos(int costoDeEspera, int costoPorServidor, int costoSistemaExtra, int costoSistema, int timeModeling) {
      this.costoEspera = costoDeEspera*this.tiempoEnCola; 
      this.costoDeServidor = costoPorServidor*this.tiempoDeServicio; 
      this.costoOperacion = costoSistema*timeModeling; 
      this.costoOperacionExtra = costoSistemaExtra*this.tiempoAdicional; 
    }
    
    public void tiempoDeServicioPromedio(){
        this.tiempoDeServicioPromedio = this.tiempoDeServicio/this.cantidadServicio;
    }
    
    private void salidasPromedio(){
        this.salidasPromedio=1/this.tiempoDeServicioPromedio;
    }
   
     public void actualizarClientesNoEsperan(){
        this.clientesNoEsperan=this.clientesNoEsperan+1;
    }
    
    public void actualizarClientesSeVan(){
        this.clientesSeVan=this.clientesSeVan+1;
    }
    
    public void calcularProbabilidadEsperar(){
        this.probabilidadDeEsperar = (100*(this.cantidadLlegadas-this.clientesNoEsperan))/this.cantidadLlegadas;
    }
    
    public void actualizarCantidadClientesEnCola(int actual){
        if(actual==0)
            this.clientesEnCola=0;
        else{
            this.clientesEnCola=this.clientesEnCola+1;
        }
    }
    
    public void calcularCantidadPromedioClientesEnCola(int tiempoFinal){
        this.clientesEnCola= this.clientesEnCola/tiempoFinal;
    }
   
    public void actualizarCantidadClientesEnSistema(int actual){
        if(actual==0)
            this.clientesEnSistema=0;
        else{
            this.clientesEnSistema= this.clientesEnSistema + 1;
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
      if(timeModeling-finishTime > 0){
        this.tiempoAdicional = timeModeling-finishTime;
      } else {
        this.tiempoAdicional = 0;
      }
    }
    
     public void actualizarPorcentajeUtilizacion(int tiempo,int servidor,int uso){
        if(tiempo==0)
            this.porcentajeUtilizacion.set(servidor,0.0);
        else
            this.porcentajeUtilizacion.set(servidor, (this.porcentajeUtilizacion.get(servidor)+uso));
    }
    
    public void calcularPorcentajeUtilizacion(int tiempoFinal){
        for(int i=0;i<this.porcentajeUtilizacion.size();i++){
            this.porcentajeUtilizacion.set(i, (100*this.porcentajeUtilizacion.get(i))/tiempoFinal);
        }
    }
    
    public void calcularPorcentajeUtilizacionGeneral(){
        double valorSumado=0;
        for(int i=0;i<this.porcentajeUtilizacion.size();i++)
            valorSumado = valorSumado + this.porcentajeUtilizacion.get(i);
        this.porcentajeUtilizacionGeneral = valorSumado/this.porcentajeUtilizacion.size();
    }

    public void actualizarPorcentajes(int timeModeling,ArrayList<Boolean> statusServer){
        for(int i=0;i<statusServer.size();i++){
            int uso;
            if(statusServer.get(i)!=false)
                uso=1;
            else
                uso=0;
            this.actualizarPorcentajeUtilizacion(timeModeling,i,uso);
        }
    }

    public String generarSalida(String unidad) {
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        return  "\n   Estadisticas :" +
                
                "\n   Cantidad de Llegadas = " + cantidadLlegadas + " llegadas" +
                "\n   Cantidad de Salidas = " + cantidadServicio + " salidas." +

                "\n   Llegadas Promedio = " + numberFormat.format(llegadasPromedio) + " llegadas" + " por " + unidad +
                "\n   Salidas Promedio = " + numberFormat.format(salidasPromedio) +  " salidas." + " por " + unidad +

                "\n\n   Cantidad de veces que un clientes no espero = " + clientesNoEsperan + " veces."+
                "\n   Cantidad de veces que un cliente se fue = " + clientesSeVan + " veces."+
                "\n   Cantidad de clientes promedio en cola = " + clientesEnCola + " clientes."+ 
                "\n   Cantidad de clientes promedio en el sistema = " + clientesEnSistema + " clientes."+
                "\n   Probabilidad de un cliente de esperar = " + numberFormat.format(probabilidadDeEsperar) + "%." +
                "\n   Porcentaje de utilizacion del sistema = " + numberFormat.format(porcentajeUtilizacionGeneral) + "%." +

                "\n\n   Tiempo entre LLegadas Promedio = " + tiempoEntreLLegadasPromedio + " por " + unidad +
                "\n   Tiempo de Servicio Promedio = " + tiempoDeServicioPromedio +  " " + unidad + "." +
                "\n   Tiempo adicional que el sistema estuvo abierto = " + tiempoAdicional + " " + unidad + "." +
                "\n   Tiempo promedio de un cliente en cola = " + tiempoEnCola + " " + unidad + "." +
                "\n   Tiempo promedio de un cliente en sistema = " + tiempoEnSistema + " " + unidad + "." +
                porcentajeUtilizacionUnitario; 
    }
}
