package UnitTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Componentes.Evento;


public class EventoTests {

  /**
    Test para metodo getAT y setAT 
  */
  @Test
  public void testSetGetAT() {
    int expectedResult = 8;
    Evento E = new Evento();
    E.setAT(expectedResult);
    assertEquals(E.getAT(), expectedResult);
  }

}
