
package Componentes;

import java.util.ArrayList;

public class StatusServers {
    private ArrayList<Integer> statusServers;
    private final int numServers;

    /**
     * @param numServers Indica el numero de servidores de la etapa
     */
    public StatusServers(int numServers) {
        this.numServers=numServers;
        this.statusServers = new ArrayList<>();
        for(int i=0;i<numServers;i++)
            this.statusServers.add(i,0); 
    }
    
    /**
     * Consulta si un determinado servidor esta ocupado
     * @param index Numero del servidor 
     * @return El numero de cliente si esta ocupado y 0 si esta libre
     */
    public int isOccupied (int index){
        return this.statusServers.get(index);
    }
    
    /**
     * Añade un cliente en un servidor especifico
     * @param index Numero del servidor
     * @param numClient Numero del cliente
     */
    public void addClient (int index,int numClient){
        this.statusServers.set(index,numClient);
    }
    
    /**
     * Substrae un cliente de un servidor especifico
     * @param numClient Numero del cliente
     * @return Numero del clieente que sale 
     */
    public int subClient (int numClient){
        for(int i=0; i<this.statusServers.size();i++){
            if(this.statusServers.get(i)==numClient){
                this.statusServers.set(i, 0);
                return i;
            }
        }
        return this.numServers;
    }
    
    /**
     * Determina el numero del proximo servidor a ser ocupado
     * @return Numero del proximo servidor libre
     */
    public int nextAvailableServer (){
        int i=0;
        for(; i<this.statusServers.size();i++){
            if(isOccupied(i)==0)
                return i;
        }
        return this.numServers;
    }

    /**
     * Retorna el numero de servidores de la etapa
     * @return Numero de servidores
     */
    public int getNumServers() {
        return numServers;
    }

    @Override
    public String toString() {
        String resultado="( ";
        for(int i=0; i<this.statusServers.size();i++){
            resultado = resultado+this.statusServers.get(i)+" ";
        }
        resultado = resultado+")";
        return resultado;
    }
    
}
