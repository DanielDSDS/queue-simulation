
package Componentes;

import java.util.ArrayList;

public class TablaArrivals {
    private ArrayList<ClienteEntrada> arrivalTimes;

    /**
     */
    public TablaArrivals() {
        this.arrivalTimes = new ArrayList<>();
    }
    
    /**
     * Añade un Cliente cuando llega al sistema
     * @param arrival Indica el tiempo de llegada
     * @param cliente Indica el numero del cliente
     */
    public void addArrival(int arrival,int cliente){
        this.arrivalTimes.add(new ClienteEntrada(arrival,cliente));
    }
    
    /**
     * Substrae un cliente cuando sale del sistema
     * @param cliente Indica el numero del cliente
     * @return El tiempo de llegada del cliente
     */
    public int subArrival(int cliente){
        for(int i=0;i<this.arrivalTimes.size();i++){
            if(this.arrivalTimes.get(i).getNumCliente() == cliente){
                int arrivalTime=this.arrivalTimes.get(i).getArrivalTime();
                this.arrivalTimes.remove(i);
                return arrivalTime;
            }
        }
        return 0;
    }
    
    /**
     * Obtiene el valor del proximo cliente a salir de la etapa o sistema
     * @return Valor de salida del proximo cliente
     */
    public int nextArrival(){
        int nextArrival=9999;
        for(int i=0;i<this.arrivalTimes.size();i++){
            if(this.arrivalTimes.get(i).getArrivalTime()<nextArrival)
                nextArrival=this.arrivalTimes.get(i).getArrivalTime();
        }
        return nextArrival;
    }
    
    /**
     * Obtiene el valor del proximo cliente a salir del sistema o etapa
     * @return Numero del cliente
     */
    public int nextClient(){
        int nextArrival = this.nextArrival();
        for(int i=0;i<this.arrivalTimes.size();i++){
            if(this.arrivalTimes.get(i).getArrivalTime()==nextArrival)
                return this.arrivalTimes.get(i).getNumCliente();
        }
        return 0;
    }
    
    /**
     * Determina el valor de llegada de un cliente en especifico
     * @param cliente Numero del cliente
     * @return El tiempo de llegada del cliente
     */
    public int valor(int cliente){
    for(int i=0;i<this.arrivalTimes.size();i++){
            if(this.arrivalTimes.get(i).getNumCliente() == cliente){
                return this.arrivalTimes.get(i).getArrivalTime();
            }
        }
    return 0;
    }
    
    public boolean isEmpty(){
        return this.arrivalTimes.isEmpty();
    }
    
    public int size(){
        return this.arrivalTimes.size();
    }

    public ArrayList<ClienteEntrada> getArrivalTimes() {
        return arrivalTimes;
    }

    @Override
    public String toString() {
        String ArrivalLine = "";
            for (int i=0; i<this.arrivalTimes.size();i++) {
                ArrivalLine = ArrivalLine + this.arrivalTimes.get(i).toString() + " ";
            }
        return ArrivalLine;
    }
    
    public String toStringSalida() {
        String ArrivalLine = "     ";
            for (int i=0; i<this.arrivalTimes.size();i++) {
                ArrivalLine = ArrivalLine + this.arrivalTimes.get(i).toStringSalida() + "\n     ";
            }
        return ArrivalLine;
    }
    
    
    
}
