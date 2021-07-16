/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *Contiente las funciones pernitenetes 
 *para la generacion de numeros aleatorios
 * @author Daniel Bermudez
 */
public class Aleatorio {
    
    private int seed;
    
    public Aleatorio(int seed){
       this.seed=seed;
    };
    
    /*Genera una semilla aleatoria dado el caso que no se pase por parametro*/
    public Aleatorio(){
      Random r=new Random();
      this.seed=(int) r.nextInt(100);
    };
    
    
    /*Genera un numero aleatorio*/
    public int generarNumero(){
      Random r=new Random(this.seed);
      return (int) r.nextInt(100);
    };
    
    
    /*Genera una sencuencia aleatoria Max de numeros de entre 0 a 99 sin repetirse*/
    public List<Integer> generarSecuencia(int Max){
       List<Integer> numeros = new ArrayList<>(Max);
       Random random = new Random();
       while(numeros.size()<Max){
          int randomNumber = random.nextInt(100);
          if(!numeros.contains(randomNumber)){
            numeros.add(randomNumber);
          };
       };
       return numeros;
    };
    
}
