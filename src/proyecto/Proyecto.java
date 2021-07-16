
package proyecto;

import Vistas.Parametros;

import Componentes.TablaDistribuciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Labam
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      /* 
        Parametros P = new Parametros();
        P.setVisible(true);
     */
      ArrayList<Integer> t = new ArrayList<Integer>();
      
      t.add(1);
      t.add(2);
      
      ArrayList<Double> p = new ArrayList<Double>();
      
      p.add(0.20);
      p.add(0.80);
      
      
      TablaDistribuciones Dis_TELL=new TablaDistribuciones(t,p);
      
      Dis_TELL.generarTabla();
      System.out.println(
              "Tabla: "+Dis_TELL.getTabla().get(0).gettiempo()+
              " / "+Dis_TELL.getTabla().get(0).getprobabilidad()+
              " / "+Dis_TELL.getTabla().get(0).getprobabilidadAcumulada()+
              " /" +Dis_TELL.getTabla().get(0).getValorMin()+"-"+Dis_TELL.getTabla().get(0).getvalorMax()
      );
      System.out.println(
              "Tabla: "+Dis_TELL.getTabla().get(1).gettiempo()+
              " / "+Dis_TELL.getTabla().get(1).getprobabilidad()+
              " / "+Dis_TELL.getTabla().get(1).getprobabilidadAcumulada()+
              " /" +Dis_TELL.getTabla().get(1).getValorMin()+"-"+Dis_TELL.getTabla().get(1).getvalorMax()
      );
     
      
      
    
     
    }
    
}
