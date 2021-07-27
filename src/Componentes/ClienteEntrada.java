
package Componentes;

public class ClienteEntrada {
    private int arrivalTime;
    private int numCliente;

    /**
     * 
     * @param arrivalTime Valor de tiempo de llegada de un cliente
     * @param numCliente Valor numerico que representa a un cliente
     */
    public ClienteEntrada(int arrivalTime, int numCliente) {
        this.arrivalTime = arrivalTime;
        this.numCliente = numCliente;
    }

    /**
     * Devuelve el tiempo de llegada del cliente
     * @return Tiempo de llegada del cliente
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Devuelve el valor numerico del cliente 
     * @return Valor numerico del cliente
     */
    public int getNumCliente() {
        return numCliente;
    }

    /**
     * Establece el valor de llegada de un cliente
     * @param arrivalTime Indica el tiempo de llegada 
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Establece el valor numerico de un cliente
     * @param numCliente Indica el numero del cliente
     */
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }
    
    
    @Override
    public String toString() {
        return "{" + "AT=" + arrivalTime + ", NroC=" + numCliente + '}';
    }  
    
    /**
     * Genera la salida del sistema
     * @return
     */
    public String toStringSalida(){
        return "{" + "DT=" + arrivalTime + ", NroC=" + numCliente + '}';
    }
    
}
