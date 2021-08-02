package UnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Componentes.Distribuciones;


public class DistribucionesTests {

  /**
    Test para metodo getTiempo  
  */
  @Test
  public void testGetTiempo() {
    int expectedResult = 20;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(expectedResult, 10.0);
    assertEquals(D.getTiempo(), expectedResult);
  }

  /**
    Test para metodo getProbabilidad  
  */
  @Test
  public void testGetProbabilidad() {
    double expectedResult = 10.00;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(20, expectedResult);
    assertEquals(D.getProbabilidad(), expectedResult);
  }

  /**
    Test para metodo setTiempo  
  */
  @Test
  public void testSetTiempo() {
    int expectedResult = 10;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(5,10);
    D.setTiempo(expectedResult);
    assertEquals(D.getTiempo(), expectedResult);
  }

  /**
    Test para metodo setProbabilidad  
  */
  @Test
  public void testSetProbabilidad() {
    int expectedResult = 10;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(5,10);
    D.setProbabilidad(expectedResult);
    assertEquals(D.getProbabilidad(), expectedResult);
  }

  /**
    Test para metodo getProbabilidadAcumulada  
  */
  @Test
  public void testGetSetProbabilidadAcumulada() {
    int expectedResult = 10;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(5,10);
    D.setProbabilidadAcumulada(expectedResult);
    assertEquals(D.getProbabilidadAcumulada(), expectedResult);
  }

  /**
    Test para metodo setGetMaxValue  
  */
  @Test
  public void testGetSetMaxValue() {
    int expectedResult = 10;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(5,10);
    D.setValorMax(expectedResult);
    assertEquals(D.getValorMax(), expectedResult);
  }

  /**
    Test para metodo setGetMaxValue  
  */
  @Test
  public void testGetSetMinValue() {
    int expectedResult = 10;
    //Distribuciones(int tiempo, double probabilidad); 
    Distribuciones D = new Distribuciones(5,10);
    D.setValorMin(expectedResult);
    assertEquals(D.getValorMin(), expectedResult);
  }

}
