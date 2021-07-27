
package Componentes;

import Funciones.Alerta;
import java.util.ArrayList;

public class TablaServicio {
    
    private ArrayList<VariablesProbabilidad> tiempoDeServicio;
    
    public TablaServicio() {
        this.tiempoDeServicio = new ArrayList<>();
    }
    
    public boolean verificarValor(int valor){
        for(int i=0;i<this.tiempoDeServicio.size();i++){
            if(valor==this.tiempoDeServicio.get(i).getValor())
                return false;
        }
        return true;
    }
    
    /**
     * Añade un nuevo tiempo de servicio a la tabla 
     * @param valor Valor del tiempo entre llegadas
     * @param porcentaje Valor del procentaje del tiempo correspondiente
     */
    public void addtiempoDeServicios(int valor, int porcentaje){
        if(porcentaje>0 && porcentaje<=100){
            if(this.tiempoDeServicio.isEmpty()){
                int margenSuperior = porcentaje-1;
                this.tiempoDeServicio.add(new VariablesProbabilidad(valor,porcentaje,porcentaje,0,margenSuperior));
                Alerta.mensajeNotificacion("Ingresado TIEMPO DE SERVICIO (valor="+ valor +", porcentaje="+ porcentaje +", acumulado="+porcentaje+")");
            }else{
                VariablesProbabilidad anterior=this.tiempoDeServicio.get(this.tiempoDeServicio.size()-1);
                if((this.tiempoDeServicio.get(this.tiempoDeServicio.size()-1).getPorcentajeAcumulado()+porcentaje)<=100){
                    int acumulado = anterior.getPorcentajeAcumulado()+porcentaje;
                    int margenSuperior = acumulado-1;
                    this.tiempoDeServicio.add(new VariablesProbabilidad(valor,porcentaje,acumulado,anterior.getMargenSuperior()+1,margenSuperior));
                    Alerta.mensajeNotificacion("Ingresado TIEMPO DE SERVICIO (valor="+ valor +", porcentaje="+ porcentaje +", acumulado="+acumulado+")");
                }else{
                    Alerta.mensajeError("Fallo al ingresar TIEMPO DE SERVICIO (valor="+ valor +", porcentaje="+ porcentaje +")\nSOBREPASA EL PORCENTAJE MAXIMO (PORCENTAJE ACTUAL="+anterior.getPorcentajeAcumulado()+")");
                }
            }
        }else {
            if(!verificarValor(valor))
                Alerta.mensajeError("Fallo al ingresar TIEMPO DE SERVICIO (valor="+ valor +", porcentaje="+ porcentaje +")\nVALOR YA INGRESADO");
            else
                Alerta.mensajeError("Fallo al ingresar TIEMPO DE SERVICIO (valor="+ valor +", porcentaje="+ porcentaje +")\nPORCENTAJE INVALIDO");
        }
    }
    
    /**
     * Obtiene el valor de tiempo de servicio a partir de un numero aleatorio 
     * y la tabla de probabilidades
     * @param random Indica el valor aleatorio para determinar el tiempo de servicio
     * @return Devuelve el valor del tiempo de servicio determinado
     */
    public int getValue(int random){
        for(int i=0;i<this.tiempoDeServicio.size();i++){
            if(random>=this.tiempoDeServicio.get(i).getMargenInferior() && random<=this.tiempoDeServicio.get(i).getMargenSuperior())
                return this.tiempoDeServicio.get(i).getValor();
        }
        return 0;
    }
    
    /**
     * Verifica que el procentaje de todos los tiempo de servicio sea del 100%
     * @return True si es 100%, False sino.
     */
    public boolean isCompleted(){
        if(this.tiempoDeServicio.isEmpty())
            return false;
        return this.tiempoDeServicio.get(this.tiempoDeServicio.size()-1).getPorcentajeAcumulado() == 100;
    }
    
    @Override
    public String toString (){
        String timeService = " Lista tiempo Servicio: ";
        for (VariablesProbabilidad tiempoDeServicios : this.tiempoDeServicio) {
            timeService = timeService+ "\n  " + tiempoDeServicios.toString();
        }
        return timeService;
    }
    
    
}
