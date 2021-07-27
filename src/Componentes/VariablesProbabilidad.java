/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

public class VariablesProbabilidad {
    private int valor;
    private int porcentaje;
    private int porcentajeAcumulado;
    private int margenInferior;
    private int margenSuperior;

    /**
     * @param valor Valor del tiempo entre llegadas o servicio
     * @param porcentaje Procentaje del valor en la tabla
     * @param porcentajeAcumulado Porcentaje Acumulado de los valores en la tabla 
     * @param margenInferior Margen Inferior para el valor 
     * @param margenSuperior Margen Superior para el valor 
     */
    public VariablesProbabilidad(int valor, int porcentaje, int porcentajeAcumulado, int margenInferior, int margenSuperior) {
        this.valor = valor;
        this.porcentaje = porcentaje;
        this.porcentajeAcumulado = porcentajeAcumulado;
        this.margenInferior = margenInferior;
        this.margenSuperior = margenSuperior;
    }

    /**
     * Obtiene el valor del tiempo entre llegada o servicio
     * @return Tiempo entre llegada o Servicio
     */
    public int getValor() {
        return valor;
    }

    /** 
     * Obtiene el Porcentaje del tiempo entre llegada o servicio
     * @return Porcentaje del tiempo entre llegada o Servicio
     */
    public int getPorcentaje() {
        return porcentaje;
    }

    /**
     * Obtiene el Porcentaje acumulado del tiempo entre llegada o servicio
     * @return Porcentaje acumulado del tiempo entre llegada o Servicio
     */
    public int getPorcentajeAcumulado() {
        return porcentajeAcumulado;
    }

    /**
     * Obtiene el Margen Inferior del tiempo entre llegada o servicio
     * @return Margen Inferior del tiempo entre llegada o servicio
     */
    public int getMargenInferior() {
        return margenInferior;
    }

    /**
     * Obtiene el Margen Superior del tiempo entre llegada o servicio
     * @return Margen Superior del tiempo entre llegada o servicio
     */
    public int getMargenSuperior() {
        return margenSuperior;
    }

    /** 
     * Establecer el Valor del tiempo entre llegadas o servicio
     * @param valor Valor del tiempo entre llegadas o servicio
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Establece el Porcentaje del tiempo entre llegadas o servicio
     * @param porcentaje Porcentaje del tiempo entre llegadas o servicio
     */
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * Establece el Porcentaje acumulado del tiempo entre llegadas o servicio
     * @param porcentajeAcumulado Porcentaje acumulado del tiempo entre llegadas o servicio
     */
    public void setPorcentajeAcumulado(int porcentajeAcumulado) {
        this.porcentajeAcumulado = porcentajeAcumulado;
    }

    /**
     * Establece el Margen Inferior del tiempo entre llegada o servicio
     * @param margenInferior Margen Inferior del tiempo entre llegada o servicio
     */
    public void setMargenInferior(int margenInferior) {
        this.margenInferior = margenInferior;
    }

    /**
     * Establece el Margen Superior del tiempo entre llegada o servicio
     * @param margenSuperior Margen Superior del tiempo entre llegada o servicio
     */
    public void setMargenSuperior(int margenSuperior) {
        this.margenSuperior = margenSuperior;
    }

    @Override
    public String toString() {
        return "Valor=" + valor + ", Porcentaje=" + porcentaje + ", Porcentaje acumulado=" + porcentajeAcumulado + ", Intervalo= [" + margenInferior + "," + margenSuperior + "]";
    }

   
    
}
