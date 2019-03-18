package TestBarcos;
        
import org.junit.Test;
import static org.junit.Assert.*;
import Clases.Barco;
import Clases.Textos;

/**
 * Clase TestBarcos que representa todos los test realizados a la clase Barcos
 * @Version 1.2
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class BarcoTest
{
    /**
     * Crea un portaaviones
     */
    @Test
    public void testCreatePortaavionesC()
    {
        Barco portaaviones = new Barco(Textos.PORTAAVIONES,5);
        
        String figura = portaaviones.getFigure();
        
        assertEquals(Textos.PORTAAVIONESFIGURE,figura);
    }
    
    @Test
    public void testCreatePortaavionesI()
    {
        Barco portaaviones = new Barco(Textos.PORTAAVIONES,5);
        
        String figura = portaaviones.getFigure();
        
        assertNotEquals("ppppp",figura);
    }
    
    /**
     * Crea un buque
     */
    @Test
    public void testCreateBuqueC()
    {
        Barco buque = new Barco(Textos.BUQUE,3);
        
        String figura = buque.getFigure();
        
        assertEquals(Textos.BUQUEFIGURE,figura);
    }
    
    @Test
    public void testCreateBuqueI()
    {
        Barco Buque = new Barco(Textos.BUQUE,5);
        
        String figura = Buque.getFigure();
        
        assertNotEquals("bbb",figura);
    }
    
    /**
     * Crea un submarino
     */
    @Test
    public void testCreateSubmarinoC()
    {
        Barco submarino = new Barco(Textos.SUBMARINO,2);
        
        String figura = submarino.getFigure();
        
        assertEquals(Textos.SUBMARINOFIGURE,figura);
    }
    
    @Test
    public void testCreateSubmarinoI()
    {
        Barco submarino = new Barco(Textos.SUBMARINO,2);
        
        String figura = submarino.getFigure();
        
        assertNotEquals("ss",figura);
    }
    
    /**
     * Crea una lancha
     */
    @Test
    public void testCreateLanchaC()
    {
        Barco lancha = new Barco(Textos.LANCHA,1);
        
        String figura = lancha.getFigure();
        
        assertEquals(String.valueOf(Textos.LANCHAFIGURE),figura);
    }
    
    @Test
    public void testCreateLanchaI()
    {
        Barco lancha = new Barco(Textos.LANCHA,5);
        
        String figura = lancha.getFigure();
        
        assertNotEquals("l",figura);
    }
    
    /**
     * A partir de la figura se obtiene el nombre del barco portaaviones
     */
    @Test
    public void testObtenerNombrePortaavionesC()
    {
        String figura = Textos.PORTAAVIONESFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.PORTAAVIONES, nombre);
    }
    
    @Test
    public void testObtenerNombrePortaavionesI()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.LANCHA, nombre);
    }
    
    /**
     * A partir de la figura se obtiene el nombre del barco buque
     */
    @Test
    public void testObtenerNombreBuqueC()
    {
        String figura = Textos.BUQUEFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.BUQUE, nombre);
    }
    
    @Test
    public void testObtenerNombreBuqueI()
    {
        String figura = Textos.BUQUEFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.PORTAAVIONES, nombre);
    }
    
    /**
     * A partir de la figura se obtiene el nombre del barco Submarino
     */
    @Test
    public void testObtenerNombreSubmarinoC()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.SUBMARINO, nombre);
    }
    
    @Test
    public void testObtenerNombreSubmarinoI()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.LANCHA, nombre);
    }
    
    /**
     * A partir de la figura se obtiene el nombre del barco lancha
     */
    @Test
    public void testObtenerNombreLanchaC()
    {
        char figura = Textos.LANCHAFIGURE;

        String nombre = Barco.obtenerNombre(String.valueOf(figura));
        
        assertEquals(Textos.LANCHA, nombre);
    }
    
    @Test
    public void testObtenerNombreLanchaI()
    {
        char figura = Textos.LANCHAFIGURE;

        String nombre = Barco.obtenerNombre(String.valueOf(figura));
        
        assertNotEquals(Textos.PORTAAVIONES, nombre);
    }
    
    /**
     * Reduce la vida del barco a 0
     */
    @Test
    public void testReducirVidaC()
    {
        Barco brc = new Barco(Textos.PORTAAVIONES,Textos.PORTAAVIONESFIGURE.length());
        
        for (int i = 0; i < brc.getLength(); i++)
        {
            brc.reducirVida();
        }
        
        assertEquals(0, brc.getVidas());
    }
    
    @Test
    public void testReducirVidaI()
    {
        Barco brc = new Barco(Textos.PORTAAVIONES,Textos.PORTAAVIONESFIGURE.length());
        
        for (int i = 0; i < brc.getLength()-1; i++)
        {
            brc.reducirVida();
        }
        
        assertNotEquals(0, brc.getVidas());
    }
    
    /**
     * Comprueba que marque el barco como hundido al quedarse sin vidas
     */
    @Test
    public void testBarcoHundidoC()
    {
        Barco brc = new Barco(Textos.PORTAAVIONES,Textos.PORTAAVIONESFIGURE.length());
        
        for (int i = 0; i < brc.getLength(); i++)
        {
            brc.reducirVida();
        }
        
        assertEquals(Textos.DEAD, brc.getState());
    }
    
    @Test
    public void testBarcoHundidoI()
    {
        Barco brc = new Barco(Textos.PORTAAVIONES,Textos.PORTAAVIONESFIGURE.length());
        
        for (int i = 0; i < brc.getLength()-1; i++)
            brc.reducirVida();
        
        assertNotEquals(Textos.DEAD, brc.getState());
    }
}
