
package Componentes;

import Funciones.Alerta;
import java.util.ArrayList;

public class TablaLlegadas {
    private ArrayList<VariablesProbabilidad> tiempoEntreLlegadas;

    public TablaLlegadas() {
        this.tiempoEntreLlegadas = new ArrayList<>();
    }
    
    /**
     * Verifica que el valor del tiempo entre llegadas no este entre los valores }
     * ya agregados
     * @param valor Valor del tiempo entre llegadas
     * @return True si no esta ya ingresado, False sino
     */
    public boolean verificarValor(int valor){
        for(int i=0;i<this.tiempoEntreLlegadas.size();i++){
            if(valor==this.tiempoEntreLlegadas.get(i).getValor())
                return false;
        }
        return true;
    }
   
    /**
     * Añade un nuevo tiempo entre llegadas a la tabla 
     * @param valor Valor del tiempo entre llegadas
     * @param porcentaje Valor del procentaje del tiempo correspondiente
     */
    public void addtiempoEntreLlegadas(int valor, int porcentaje){
        
        if(porcentaje>0 && porcentaje<=100 && verificarValor(valor) ){
            if(this.tiempoEntreLlegadas.isEmpty()){
                int margenSuperior = porcentaje-1;
                this.tiempoEntreLlegadas.add(new VariablesProbabilidad(valor,porcentaje,porcentaje,0,margenSuperior));
               Alerta.mensajeNotificacion("Ingresado TIEMPO ENTRE LLEGADAS (valor="+ valor +", porcentaje="+ porcentaje +", acumulado="+porcentaje+")");
            }else{
                VariablesProbabilidad anterior=this.tiempoEntreLlegadas.get(this.tiempoEntreLlegadas.size()-1);
                if((this.tiempoEntreLlegadas.get(this.tiempoEntreLlegadas.size()-1).getPorcentajeAcumulado()+porcentaje)<=100){
                    int acumulado = anterior.getPorcentajeAcumulado()+porcentaje;
                    int margenSuperior = acumulado-1;
                    this.tiempoEntreLlegadas.add(new VariablesProbabilidad(valor,porcentaje,acumulado,anterior.getMargenSuperior()+1,margenSuperior));
                   Alerta.mensajeNotificacion("Ingresado TIEMPO ENTRE LLEGADAS (valor="+ valor +", porcentaje="+ porcentaje +", acumulado="+acumulado+")");
                }else{
                    Alerta.mensajeError("Fallo al ingresar TIEMPO ENTRE LLEGADAS (valor="+ valor +", porcentaje="+ porcentaje +")\nSOBREPASA EL PORCENTAJE MAXIMO (PORCENTAJE ACTUAL="+anterior.getPorcentajeAcumulado()+")");
                }
            }
        } else {
            if(!verificarValor(valor))
                Alerta.mensajeError("Fallo al ingresar TIEMPO ENTRE LLEGADAS (valor="+ valor +", porcentaje="+ porcentaje +")\nVALOR YA INGRESADO");
            else
                Alerta.mensajeError("Fallo al ingresar TIEMPO ENTRE LLEGADAS (valor="+ valor +", porcentaje="+ porcentaje +")\nPORCENTAJE INVALIDO");
        }
    }
    
    /**
     * Obtiene el valor de tiempo entre llegadas a partir de un numero aleatorio 
     * y la tabla de probabilidades
     * @param random Indica el valor aleatorio para determinar el tiempo entre 
     * llegadas
     * @return Devuelve el valor del tiempo entre llegadas determinado
     */
    public int getValue(int random){
        for(int i=0;i<this.tiempoEntreLlegadas.size();i++){
            if(random>=this.tiempoEntreLlegadas.get(i).getMargenInferior() && random<=this.tiempoEntreLlegadas.get(i).getMargenSuperior())
                return this.tiempoEntreLlegadas.get(i).getValor();
        }
        return 0;
    }
    
    /**
     * Verifica que el procentaje de todos los tiempo entre llegadas sea del 100%
     * @return True si es 100%, False sino.
     */
    public boolean isCompleted(){
        if(this.tiempoEntreLlegadas.isEmpty())
            return false;
        return this.tiempoEntreLlegadas.get(this.tiempoEntreLlegadas.size()-1).getPorcentajeAcumulado() == 100;
    }
    
    @Override
    public String toString (){
        String timeEntries = " Lista tiempo Llegadas: ";
        for (VariablesProbabilidad tiempoEntreLlegada : this.tiempoEntreLlegadas) {
            timeEntries = timeEntries+ "\n  " + tiempoEntreLlegada.toString();
        }

        return timeEntries;
    }
}
