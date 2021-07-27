
package Componentes;

import java.util.ArrayList;

public class TablaWaiting {
    private ArrayList<Integer> waiting;
    private int capacidad;
    
    /**
     * @param numeroServers Numero de servidores de la etapa 
     * @param capacidadMaxima Capacidad maxima del sistema
     */
    public TablaWaiting(int numeroServers, int capacidadMaxima) {
        this.waiting = new ArrayList<>();
        this.capacidad=capacidadMaxima-numeroServers;
    }
    
    /**
     * Añade un cliente a la cola de espera
     * @param cliente Numero del cliente 
     * @return Retorna 1 si se pudo añadir, 0 si no
     */
    public int addWaiting(int cliente){
        if(this.waiting.size()<capacidad){
            waiting.add(cliente);
            return 1;
        }
        return 0;
    }
    
    /**
     * Obtiene el tamaño de la cola
     * @return Tamaño de la cola
     */
    public int cantidadCola(){
        return this.waiting.size();
    }
    
    /**
     * Substrae a un cliente de la cola de espera
     * @return Numero del cliente si se pudo, 0 si no
     */
    public int subWaiting(){
        if(this.waiting.size()>0){
            int client= waiting.get(0);
            waiting.remove(0);
            return client;
        }
        return 0;
    }
    
    /**
     * Determina si la cola esta vacia 
     * @return True si esta vacia, False si no
     */
    public boolean isEmpty(){
        return this.waiting.isEmpty();
    }

    @Override
    public String toString() {
        String waitingLine = "Waiting List: (";
        for (int i=0; i<this.waiting.size();i++) {
            waitingLine = waitingLine + " " + this.waiting.get(i) ;
        }
        return "( "+waitingLine+" ) )";
    }
    
    
}
