
package Componentes;

public class ClienteSalida {
    private int departureTime;
    private int numCliente;
    private int tiempoServicio;

    /**
     * @param departureTime Valor de tiempo de salida del cliente
     * @param tiempoServicio Valor del tiempo de servicio del cliente
     * @param numCliente Valor numerico que representa a un cliente
     */
    public ClienteSalida(int departureTime,int tiempoServicio, int numCliente) {
        this.departureTime = departureTime;
        this.numCliente = numCliente;
        this.tiempoServicio = tiempoServicio;
    }

    /**
     * Devuelve el tiempo de salida del cliente
     * @return Tiempo de salida del cliente
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * Devuelve el valor numerico del cliente 
     * @return Numero del cliente
     */
    public int getNumCliente() {
        return numCliente;
    }

    /**
     * Establece el tiempo de salida del cliente
     * @param departureTime Indica el tiempo de salida
     */
    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Establece el valor numerico de un cliente
     * @param numCliente Indica el numero del cliente
     */
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * Devuelve el valor del tiempo de servicio del cliente
     * @return Tiempo de servicio del cliente
     */
    public int getTiempoServicio() {
        return tiempoServicio;
    }

    /**
     * Establece el valor del tiempo de servicio del cliente
     * @param tiempoServicio Indica el tiempo de servicio del cliente
     */
    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    @Override
    public String toString() {
        return "{" + "DT=" + departureTime + ", NroC=" + numCliente + '}';
    }  
}
