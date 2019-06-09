package Main;

import Controlador.ControladorVentanaPrincipal;
import Vista.VentanaPrincipal;

/**
 * @author Pablo Oraa Lopez
 */
public class Main
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        VentanaPrincipal vp = new VentanaPrincipal();
        new ControladorVentanaPrincipal(vp);
        vp.setVisible(true);
    }
}
