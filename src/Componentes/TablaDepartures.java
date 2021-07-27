
package Componentes;

import java.util.ArrayList;

public class TablaDepartures {
    private ArrayList<ClienteSalida> departureTimes;
    private final int numServers;

    /**
     * @param numServers Indica el numero de servidores de la etapa o sistema
     */
    public TablaDepartures(int numServers) {
        this.numServers=numServers;
        this.departureTimes = new ArrayList<>();
    }
    
    /**
     * Añade una salida cuando llega un cliente al sistema
     * @param departure Indica el tiempo de salida del cliente
     * @param tiempoServicio Indica el tiempo de servicio del cliente
     * @param cliente Indica el numero del cliente
     */
    public void addDeparture(int departure,int tiempoServicio,int cliente){
        if(this.departureTimes.size()<this.numServers)
            this.departureTimes.add(new ClienteSalida(departure,tiempoServicio,cliente));
    }
    
    /**
     * Substrae un cliente cuando sale del sistema
     * @param cliente Indica el numero del cliente
     */
    public void subDeparture(int cliente){
        for(int i=0;i<this.departureTimes.size();i++){
            if(this.departureTimes.get(i).getNumCliente() == cliente)
                this.departureTimes.remove(i);
        }
    }
    
    /**
     * Obtiene el valor del proximo cliente a salir de la etapa o sistema
     * @return Valor de salida del proximo cliente
     */
    public int nextDeparture(){
        int nextDeparture=9999;
        for(int i=0;i<this.departureTimes.size();i++){
            if(this.departureTimes.get(i).getDepartureTime()<nextDeparture)
                nextDeparture=this.departureTimes.get(i).getDepartureTime();
        }
        return nextDeparture;
    }
    
    /**
     * Obtiene el valor del proximo cliente a salir del sistema o etapa
     * @return Numero del cliente
     */
    public int nextClient(){
        int nextDeparture = this.nextDeparture();
        for(int i=0;i<this.departureTimes.size();i++){
            if(this.departureTimes.get(i).getDepartureTime()==nextDeparture)
                return this.departureTimes.get(i).getNumCliente();
        }
        return 0;
    }
    
    /**
     * Obtiene el valor de servicio del proximo cliente a salir del sistema 
     * o etapa
     * @return Valor del tiempo de servicio del cliente
     */
    public int nextService(){
        int nextDeparture = this.nextDeparture();
        for(int i=0;i<this.departureTimes.size();i++){
            if(this.departureTimes.get(i).getDepartureTime()==nextDeparture)
                return this.departureTimes.get(i).getTiempoServicio();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        if(this.departureTimes.isEmpty()){
            return "9999";
        }else{
            String DepartureLine = "";
            for (int i=0; i<this.departureTimes.size();i++) {
                DepartureLine = DepartureLine + this.departureTimes.get(i).toString() + " ";
            }
            return DepartureLine;
        }
    }
}
