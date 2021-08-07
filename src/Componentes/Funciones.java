/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Funciones {

  public double tiempoEntreLLegadas;
  public double tiempoEntreLLegadasPromedio;
  public int cantidadLlegadas;
  public double tiempoDeServicio;
  public double tiempoDeServicioPromedio;
  public int cantidadServicio;
  public double clientesNoEsperan;
  public double clientesSeVan;
  public double probabilidadDeEsperar;
  public double clientesEnCola;
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
    this.cantidadLlegadas = 0;

    this.tiempoDeServicio = 0;
    this.tiempoDeServicioPromedio = 0;
    this.cantidadServicio = 0;

    this.clientesNoEsperan = 0;
    this.clientesSeVan = 0;

    this.probabilidadDeEsperar = 0;

    this.clientesEnCola = 0;

    this.tiempoEnCola = 0;
    this.tiempoEnSistema = 0;

    this.tiempoAdicional = 0;

    this.costoDeServidor = 0;
    this.costoOperacion = 0;
    this.costoOperacionExtra = 0;
    this.costoEspera = 0;

    this.porcentajeUtilizacionGeneral = 0;
    this.porcentajeUtilizacion = new ArrayList<>();
    for (int i = 0; i <= numServers; i++) this.porcentajeUtilizacion.add(0.0);
  }

  public void calcularPromedios(int timeModeling) {
    this.calcularProbabilidadEsperar();
    this.calcularTiempoPromedioClienteEnCola();
    this.calcularPorcentajeUtilizacion(timeModeling);
    this.calcularPorcentajeUtilPromedio();
  }

  public void calcularCostos(
    int costoDeEspera,
    int costoPorServidor,
    int costoSistemaExtra,
    int costoSistema,
    int timeModeling
  ) {
    this.costoEspera = costoDeEspera * this.tiempoEnCola;
    this.costoDeServidor = costoPorServidor * this.tiempoDeServicio;
    this.costoOperacion = costoSistema * timeModeling;
    this.costoOperacionExtra = costoSistemaExtra * this.tiempoAdicional;
  }

  public void actualizarCantidadLlegadas() {
    this.cantidadLlegadas = this.cantidadLlegadas + 1;
  }

  public void actualizarTiemposDeServicio(int tiempo) {
    if (this.tiempoDeServicio == 0) {
      this.tiempoDeServicio = tiempo;
    } else {
      this.tiempoDeServicio = this.tiempoDeServicio + tiempo;
    }
  }

  public void actualizarClientesNoEsperan() {
    this.clientesNoEsperan = this.clientesNoEsperan + 1;
  }

  public void actualizarClientesSeVan() {
    this.clientesSeVan = this.clientesSeVan + 1;
  }

  public void calcularProbabilidadEsperar() {
    this.probabilidadDeEsperar =
      (100 * (this.cantidadLlegadas - this.clientesNoEsperan)) /
      this.cantidadLlegadas;
  }

  public void actualizarCantidadClientesEnCola(int actual) {
    if (actual == 0) this.clientesEnCola = 0; else {
      this.clientesEnCola = this.clientesEnCola + 1;
    }
  }

  public void actualizarTiempoClienteEnSistema(int entrada, int salida) {
    this.tiempoEnSistema = this.tiempoEnSistema + (salida - entrada);
  }

  public void actualizarTiempoClienteEnCola(
    int entrada,
    int salida,
    int servicio
  ) {
    this.tiempoEnCola = this.tiempoEnCola + (salida - entrada - servicio);
  }

  public void calcularTiempoPromedioClienteEnCola() {
    this.tiempoEnCola = this.tiempoEnCola / this.cantidadLlegadas;
  }

  public void calcularTiempoAdicional(int timeModeling, int finishTime) {
    if (timeModeling - finishTime > 0) {
      this.tiempoAdicional = timeModeling - finishTime;
    } else {
      this.tiempoAdicional = 0;
    }
  }

  public void actualizarPorcentajeUtilizacion(
    int tiempo,
    int servidor,
    int uso
  ) {
    this.porcentajeUtilizacion.set(
        servidor,
        this.porcentajeUtilizacion.get(servidor) + tiempo * uso
      );
  }

  public void actualizarPorcentajes(
    int prevTime,
    int timeModeling,
    StatusServers statusServer
  ) {
    for (int i = 0; i < statusServer.getServidores().size(); i++) {
      int uso;
      if (statusServer.isOccupied(i) == 0) uso = 0; else uso = 1;
      this.actualizarPorcentajeUtilizacion(timeModeling - prevTime, i, uso);
    }
  }

  public void calcularPorcentajeUtilizacion(int tiempoFinal) {
    for (int i = 0; i < this.porcentajeUtilizacion.size(); i++) {
      this.porcentajeUtilizacion.set(
          i,
          this.porcentajeUtilizacion.get(i) / tiempoFinal
        );
    }
  }

  public void calcularPorcentajeUtilPromedio() {
    double valorSumado = 0;
    for (int i = 0; i < this.porcentajeUtilizacion.size(); i++) valorSumado +=
      this.porcentajeUtilizacion.get(i);
    this.porcentajeUtilizacionGeneral =
      (valorSumado / (this.porcentajeUtilizacion.size() - 1));
  }

  public String generarSalida(String unidad) {
    DecimalFormat numberFormat = new DecimalFormat("0.00");
    String pocentajeUtilizacionPorServidor = " ";
    for (int i = 0; i < this.porcentajeUtilizacion.size() - 1; i++) {
      pocentajeUtilizacionPorServidor +=
        "\n s" +
        i +
        " :" +
        numberFormat.format(100 * this.porcentajeUtilizacion.get(i)) +
        " %";
    }
    return (
      "\n   Estadisticas :" +
      "\n\n   Cantidad de veces que un clientes no espero = " +
      clientesNoEsperan +
      " veces." +
      "\n   Cantidad de veces que un cliente se fue sin ser atendido= " +
      clientesSeVan +
      " veces." +
      "\n   Probabilidad de un cliente de esperar = " +
      numberFormat.format(probabilidadDeEsperar) +
      "%." +
      "\n   Porcentaje de utilizacion del sistema = " +
      numberFormat.format(100 * porcentajeUtilizacionGeneral) +
      "%." +
      "\n   Tiempo promedio de un cliente en cola = " +
      tiempoEnCola +
      " " +
      unidad +
      "." +
      "\n   Tiempo promedio de un cliente en sistema = " +
      tiempoEnSistema +
      " " +
      unidad +
      "." +
      "\n  Utilizacion por servidor = " +
      pocentajeUtilizacionPorServidor
    );
  }
}
